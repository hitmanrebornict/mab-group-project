package com.example.mab_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView displayNutrientsButton;
    private ImageView searchFoodButton;
    private ImageView bmiButton;
    private ImageView recipeButton;
    private ImageView setupDailyIntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        displayNutrientsButton = findViewById(R.id.image_1);
        searchFoodButton = findViewById(R.id.image_2);
        setupDailyIntake = findViewById(R.id.image_3);
        bmiButton = findViewById(R.id.image_4);
        recipeButton = findViewById(R.id.image_5);

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

        bmiButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, bmiActivity.class);
            startActivity(intent);
        });

        recipeButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PremiumRecipe.class);
            startActivity(intent);
        });
    }
    }
