package com.example.mab_group_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DisplayNutrients extends AppCompatActivity {

    //basic function:
    //get rows from database, using timestamp as filter
    //database returns an arraylist of meal objects
    //separate them based on their meal types (b, l, d, s)
    //then, display them to users & add another button for them to add new meal

    DatabaseHandler handler = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_nutrients);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = dateFormat.format(new Date());
        DecimalFormat decfor = new DecimalFormat("0.00");

        String[] timestamp = {curDate};

        ArrayList<Meal> todayMeal;
        todayMeal = handler.getTodayMeals(timestamp);

        if (todayMeal.isEmpty()) {
            Log.i("todayMeal", "is empty");
        }

        float calories = 0;
        float carbs = 0;
        float protein = 0;
        float vitaminc = 0;
        float fat = 0;

        for (int i = 0; i < todayMeal.size(); i++) {
            calories += todayMeal.get(i).getCalories();
            carbs += todayMeal.get(i).getCarbs();
            protein += todayMeal.get(i).getProtein();
            vitaminc += todayMeal.get(i).getVitaminc();
            fat += todayMeal.get(i).getFat();
            Log.i("today meal", calories + " " + carbs + " " + protein + " " + vitaminc + " " + fat);
        }

        Log.i("today meal", calories + " " + carbs + " " + protein + " " + vitaminc + " " + fat);
        SharedPreferences sp = getSharedPreferences("dailyNutrients", MODE_PRIVATE);
        float caloriesL = sp.getInt("Calories", 0);
        float carbsL = sp.getInt("Carbs", 0);
        float proteinL = sp.getInt("Protein", 0);
        float vitamincL = sp.getInt("Vitamin_C", 0);
        float fatL = sp.getInt("Fat", 0);
        Log.i("total nutrient", caloriesL + " " + carbsL + " " + proteinL + " " + vitamincL + " " + fatL);

        TextView tvTitle = findViewById(R.id.listTodayMeals);
        tvTitle.setText("Today is " + curDate + "\n\nMeal & Nutrients taken today: ");

        TextView tvCalories = findViewById(R.id.caloriesAmount);
        tvCalories.setText("\nCalories:  " + decfor.format(calories) + "/" + caloriesL + "kCal");
        TextView tvCarbs = findViewById(R.id.carbsAmount);
        tvCarbs.setText("\nCarbohydrates: " + decfor.format(carbs) + "/" + carbsL + "g");
        TextView tvProtein = findViewById(R.id.proteinAmount);
        tvProtein.setText("\nProtein: " + decfor.format(protein) + "/" + proteinL + "g");
        TextView tvVitaminC = findViewById(R.id.vitamincAmount);
        tvVitaminC.setText("\nVitamin C: " + decfor.format(vitaminc) + "/" + vitamincL + "mg");
        TextView tvFat = findViewById(R.id.fatAmount);
        tvFat.setText("\nFat: " + decfor.format(fat) + "/" + fatL + "g");

        String mealandtype = "\n\nMeals taken for today: \n\n";

        if (todayMeal.size() == 0) mealandtype += "No meals have been taken for today.";
        for (int i = 0; i < todayMeal.size(); i++) {
            mealandtype += "Meal: " + todayMeal.get(i).getMeal() + "\nMeal Type: " + todayMeal.get(i).getMealType() + "\n\n";
        }

        TextView tvMeals = findViewById(R.id.todayMeals);
        tvMeals.setText(mealandtype);

        Button btnRecordMeal = findViewById(R.id.btnRecordMeal);
        btnRecordMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayNutrients.this, RecordMeals.class);
                DisplayNutrients.this.startActivity(intent);
            }
        });
    }
}