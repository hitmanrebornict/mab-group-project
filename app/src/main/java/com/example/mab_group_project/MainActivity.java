package com.example.mab_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FoodDataCentralApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = new FoodDataCentralApi();
        searchFoods("apple");
        getFoodDetails(1750348 );
    }

    private void searchFoods(String query) {
        new Thread(() -> {
            try {
                List<Food> foods = api.searchFoods(query);
                for (Food food : foods) {
                    Log.d("Food", food.getDescription() + " " + food.getFdcId());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void getFoodDetails(long id) {
        new Thread(() -> {
            try {
                Food food = api.getFoodDetails(id);
                Log.d("Food Desc", food.getDescription());
                Food.FoodNutrient[] foodNutrients = food.getFoodNutrients();
                Log.d("Nutrient size: ", Integer.toString(foodNutrients.length));
                for (Food.FoodNutrient foodNutrient : foodNutrients) {
                    Log.d("Nutrient name:", foodNutrient.nutrient.getName());
                    Log.d("Nutrient Amount: ", Float.toString(foodNutrient.getAmount()) + foodNutrient.nutrient.getUnitName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}