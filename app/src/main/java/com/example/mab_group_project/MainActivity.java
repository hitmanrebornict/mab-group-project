package com.example.mab_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button displayNutrientsButton;
    private Button searchFoodButton;

    private Button setupDailyIntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        displayNutrientsButton = findViewById(R.id.button_display_nutrients);
        searchFoodButton = findViewById(R.id.button_search_food);
        setupDailyIntake = findViewById(R.id.button_setup_daily_nutrients);

        displayNutrientsButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DisplayNutrients.class);
            startActivity(intent);
        });

        searchFoodButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SearchFoodModule.class);
            startActivity(intent);
        });

        setupDailyIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetupForRecordMeal.class);
                startActivity(intent);
            }
        });
    }
}