package com.example.mab_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchFoodModule extends AppCompatActivity {

    private EditText searchEditText;
    private ListView foodListView;
    private ArrayAdapter<String> foodListAdapter;
    private Button searchButton;
    private Spinner calorieFilterSpinner;
    private int selectedFilterLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food_module);

        // Initialize UI components
        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        foodListView = findViewById(R.id.food_list_view);

        foodListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        foodListView.setAdapter(foodListAdapter);


        // Set up search bar listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFood(searchEditText.getText().toString());
                Log.e("a", "a");
            }
        });

        calorieFilterSpinner = findViewById(R.id.calorie_filter_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.calorie_filter_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calorieFilterSpinner.setAdapter(adapter);
        calorieFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFilterLevel = position;
                searchFood(searchEditText.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void updateFoodList(List<Pair<String, String>> foodInfoList) {
        foodListAdapter.clear();

        // Sort food items based on calories (if available)
        Collections.sort(foodInfoList, new Comparator<Pair<String, String>>() {
            @Override
            public int compare(Pair<String, String> o1, Pair<String, String> o2) {
                String calorieInfo1 = o1.second;
                String calorieInfo2 = o2.second;

                if (calorieInfo1.equals("unknown") && calorieInfo2.equals("unknown")) {
                    return 0;
                }
                if (calorieInfo1.equals("unknown")) {
                    return 1;
                }
                if (calorieInfo2.equals("unknown")) {
                    return -1;
                }

                double calories1 = Double.parseDouble(calorieInfo1.split(" ")[0]);
                double calories2 = Double.parseDouble(calorieInfo2.split(" ")[0]);

                return Double.compare(calories1, calories2);
            }
        });

        for (Pair<String, String> foodInfo : foodInfoList) {
            String foodName = foodInfo.first;
            String calorieInfo = foodInfo.second;

            // Check if the calorie information is available
            if (!calorieInfo.equals("unknown")) {
                // Apply calorie filter
                double calories = Double.parseDouble(calorieInfo.split(" ")[0]);
                if ((selectedFilterLevel == 1 && calories >= 500) ||
                        (selectedFilterLevel == 2 && (calories < 500 || calories >= 1000)) ||
                        (selectedFilterLevel == 3 && (calories < 1000 || calories >= 1500)) ||
                        (selectedFilterLevel == 4 && calories < 1500)) {
                    continue;
                }
            }

            String displayText = foodName + "\nCalories: " + calorieInfo;
            foodListAdapter.add(displayText);
        }
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFood = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(SearchFoodModule.this, NutritionActivity.class);
                intent.putExtra("foodName", selectedFood);
                startActivity(intent);
            }
        });
        foodListAdapter.notifyDataSetChanged();
    }

    private class FoodSearchTask extends AsyncTask<String, Void, List<Pair<String, String>>> {


        @Override
        protected List<Pair<String, String>> doInBackground(String... foodNames) {
            // Get the food name from the input arguments
            String foodName = foodNames[0];

            // Create URL to access USDA National Nutrient Database API
            String apiURL = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=XdtXmavVxBSKxoG2iPyDxpWsXAHAg0tk27UnKFhe&query=" + foodName;

            Log.e("a", "b");
            try {
                // Set up HTTP connection
                URL url = new URL(apiURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");

                // Read response from API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray foodsArray = jsonObject.getJSONArray("foods");
                List<Pair<String, String>> foodInfoList = new ArrayList<>();

                // Extract food names and calorie information from JSON array
                for (int i = 0; i < foodsArray.length(); i++) {
                    JSONObject foodObject = foodsArray.getJSONObject(i);
                    foodName = foodObject.getString("description");
                    String calorieInfo = "unknown";
                    if (foodObject.has("foodNutrients")) {
                        JSONArray nutrientsArray = foodObject.getJSONArray("foodNutrients");
                        for (int j = 0; j < nutrientsArray.length(); j++) {
                            JSONObject nutrientObject = nutrientsArray.getJSONObject(j);
                            if (nutrientObject.has("nutrientName") && nutrientObject.getString("nutrientName").equals("Energy")) {
                                calorieInfo = nutrientObject.getDouble("value") + " " + nutrientObject.getString("unitName");
                                break;
                            }
                        }
                    }
                    foodInfoList.add(new Pair<>(foodName, calorieInfo));
                }

                // Return list of food names and calorie information
                Log.e("a", "Food info list: " + foodInfoList.toString());
                return foodInfoList;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.singletonList(new Pair<>("Error searching for food information.", ""));
            }
        }

        @Override
        protected void onPostExecute(List<Pair<String, String>> result) {
            // Display results
            updateFoodList(result);
            Log.e("a", "c");
        }
    }


    private void searchFood(String foodName) {

        try {
            // Execute the FoodSearchTask to perform the network operation in the background
            Log.e("a", "d");
            new FoodSearchTask().execute(foodName);
        } catch (Exception e) {
            Log.e("a", "Exception while executing FoodSearchTask", e);
        }
    }
}