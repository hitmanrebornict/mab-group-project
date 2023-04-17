package com.example.mab_group_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private ListView foodListView;
    private ArrayAdapter<String> foodListAdapter;
    private Button searchButton;
    private int resultsPerPage = 10;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        foodListView = findViewById(R.id.food_list_view);

        foodListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        foodListView.setAdapter(foodListAdapter);
        addLoadMoreButton();


        // Set up search bar listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFood(searchEditText.getText().toString());
                Log.e("a", "a");
            }
        });
    }

    private void updateFoodList(List<String> foodNames) {
        foodListAdapter.addAll(foodNames);
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFood = (String) parent.getItemAtPosition(position);
            }
        });
        foodListAdapter.notifyDataSetChanged();
    }

    private class FoodSearchTask extends AsyncTask<String, Void, List<String>> {
        private int resultsPerPage;
        private int pageNumber;

        public FoodSearchTask(int resultsPerPage, int pageNumber) {
            this.resultsPerPage = resultsPerPage;
            this.pageNumber = pageNumber;
        }

        @Override
        protected List<String> doInBackground(String... foodNames) {
            // Get the food name from the input arguments
            String foodName = foodNames[0];

            // Create URL to access USDA National Nutrient Database API
            String apiURL = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=XdtXmavVxBSKxoG2iPyDxpWsXAHAg0tk27UnKFhe&query=" + foodName + "&pageSize=" + resultsPerPage + "&pageNumber=" + pageNumber;

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
                List<String> foodNamesList = new ArrayList<>();

                // Extract food names from JSON array
                for (int i = 0; i < foodsArray.length(); i++) {
                    JSONObject foodObject = foodsArray.getJSONObject(i);
                    foodName = foodObject.getString("description");
                    foodNamesList.add(foodName);
                }

                // Return list of food names
                Log.e("a", "Food names list: " + foodNamesList.toString());
                return foodNamesList;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.singletonList("Error searching for food information.");
            }
        }

        @Override
        protected void onPostExecute(List<String> result) {
            // Display results
            updateFoodList(result);
            Log.e("a", "c");
        }
    }

    private void searchFood(String foodName) {

        try {
        // Execute the FoodSearchTask to perform the network operation in the background
        Log.e("a", "d");
            new FoodSearchTask(resultsPerPage, currentPage).execute(foodName);
        } catch (Exception e) {
            Log.e("a", "Exception while executing FoodSearchTask", e);
        }
    }

    private void addLoadMoreButton() {
        Button loadMoreButton = new Button(this);
        loadMoreButton.setText("Load More");
        loadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage++;
                searchFood(searchEditText.getText().toString());
            }
        });
        foodListView.addFooterView(loadMoreButton);
    }
}
