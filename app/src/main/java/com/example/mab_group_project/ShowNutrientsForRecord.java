package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowNutrientsForRecord extends AppCompatActivity {

    private FoodDataCentralApi api;

    TextView tvShowInfo;
    Button btnStore;
    DatabaseHandler handler = new DatabaseHandler(this);
    Spinner spinnerMealType;
    Map<String, Float> nutrientAmounts = new HashMap<>();
    String mealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_nutrients_for_record);

        Intent intent = getIntent();
        long fdcid = intent.getLongExtra("foodfdcid", 0);
        api = new FoodDataCentralApi();
        getFoodDetails(fdcid);
        tvShowInfo = findViewById(R.id.tvshowinfo);
        tvShowInfo.setMovementMethod(new ScrollingMovementMethod());

        String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Snacks"};
        spinnerMealType = findViewById(R.id.spinnerMealType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mealTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(adapter);

        spinnerMealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mealType = mealTypes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnStore = findViewById(R.id.btnAddAsMeal);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Test", f.getDescription());
                Food.FoodNutrient[] fNuts = f.getFoodNutrients();

                for (Food.FoodNutrient fNut : fNuts) {
                    Log.i("Test", fNut.nutrient.getName() + fNut.getAmount());
                    nutrientAmounts.put(fNut.nutrient.getName(), fNut.getAmount());
                }

                saveMeal();
                finish();
            }
        });
    }

    public void saveMeal() {
        float calories = nutrientAmounts.get("Energy");
        float carbs = nutrientAmounts.get("Carbohydrate, by difference");
        float protein = nutrientAmounts.get("Protein");
        float vitaminc = nutrientAmounts.get("Vitamin C, total ascorbic acid");
        float fat = nutrientAmounts.get("Total lipid (fat)");

        if (!nutrientAmounts.containsKey("Energy")) calories = 0;
        if (!nutrientAmounts.containsKey("Carbohydrate, by difference")) carbs = 0;
        if (!nutrientAmounts.containsKey("Protein")) protein = 0;
        if (!nutrientAmounts.containsKey("Vitamin C, total ascorbic acid")) vitaminc = 0;
        if (!nutrientAmounts.containsKey("Total lipid (fat)")) fat = 0;


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timestamp = dateFormat.format(new Date());

        handler.addRow(f.getDescription(), mealType, timestamp, calories, carbs, protein, fat, vitaminc);
        Toast toast = Toast.makeText(ShowNutrientsForRecord.this, "Meal Added", Toast.LENGTH_SHORT);
        toast.show();
    }

    String strFoodInfo = "";
    Food f = null;

    private void getFoodDetails(long id) {
        new Thread(() -> {
            try {
                Food food = api.getFoodDetails(id);
                Log.d("Food Desc", food.getDescription());
                strFoodInfo += "Food Description: " + food.getDescription() + "\n\n";
                Food.FoodNutrient[] foodNutrients = food.getFoodNutrients();
                Log.d("Nutrient size: ", Integer.toString(foodNutrients.length));
                for (Food.FoodNutrient foodNutrient : foodNutrients) {
                    strFoodInfo += "Nutrient Name: " + foodNutrient.nutrient.getName() +
                    "\nNutrient Amount: " + foodNutrient.getAmount() + foodNutrient.nutrient.getUnitName() + "\n\n";
                }
                Log.i("strFoodInfo", strFoodInfo);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvShowInfo.setText(strFoodInfo);
                        f = food;

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}