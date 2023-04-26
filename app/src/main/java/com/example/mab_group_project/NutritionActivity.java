package com.example.mab_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NutritionActivity extends AppCompatActivity {

    private TextView foodNameTextView;
    private ImageView foodImageView;
    private ListView nutritionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        foodNameTextView = findViewById(R.id.food_name_text_view);
        foodImageView = findViewById(R.id.food_image_view);
        nutritionListView = findViewById(R.id.nutrition_list_view);

        String foodName = getIntent().getStringExtra("foodName");
        foodNameTextView.setText(foodName);
        new FetchNutritionTask().execute(foodName);
    }

    private class FetchNutritionTask extends AsyncTask<String, Void, List<String>> {
        private String imageURL;

        @Override
        protected List<String> doInBackground(String... foodNames) {
            String foodName = foodNames[0];
            String apiURL = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=XdtXmavVxBSKxoG2iPyDxpWsXAHAg0tk27UnKFhe&query=" + foodName;

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

                Log.d("API_RESPONSE", response.toString());

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray foodsArray = jsonObject.getJSONArray("foods");

                // Check if any food items are returned
                if (foodsArray.length() == 0) {
                    return Collections.singletonList("No nutrition information available for this food item.");
                }

                JSONObject foodObject = foodsArray.getJSONObject(0);
                JSONArray nutrientsArray = foodObject.getJSONArray("foodNutrients");



                // Build the nutrition information list
                List<String> nutritionInfoList = new ArrayList<>();
                for (int i = 0; i < nutrientsArray.length(); i++) {
                    JSONObject nutrientObject = nutrientsArray.getJSONObject(i);
                    String nutrientName = nutrientObject.getString("nutrientName");
                    double nutrientValue = nutrientObject.getDouble("value");
                    String nutrientUnit = nutrientObject.getString("unitName");

                    nutritionInfoList.add(nutrientName + ": " + nutrientValue + " " + nutrientUnit);
                }

                // Sort the list alphabetically
                Collections.sort(nutritionInfoList);

                // Get the image URL from the food object
                if (foodObject.has("imageUrl")) {
                    imageURL = foodObject.getString("imageUrl"); // Update this line
                }

                return nutritionInfoList;
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.singletonList("Error retrieving nutrition information.");
            }
        }

        @Override
        protected void onPostExecute(List<String> result) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(NutritionActivity.this, android.R.layout.simple_list_item_1, result);
            nutritionListView.setAdapter(adapter);

            if (imageURL != null) {
                Picasso.get().load(imageURL).into(foodImageView);
            } else {
                // Load a default image as a fallback
                Picasso.get().load(R.drawable.int3).into(foodImageView);
            }
        }
    }
}