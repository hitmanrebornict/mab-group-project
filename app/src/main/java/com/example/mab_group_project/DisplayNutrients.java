package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
        tvTitle.setText("Today is " + curDate + "\nBelow are the nutrients you have consumed for today and your meal taken today: ");

        TextView tvCalories = findViewById(R.id.caloriesAmount);
        tvCalories.setText("Today's Calories Amount: " + decfor.format(calories) + "/" + caloriesL + "kCal");
        TextView tvCarbs = findViewById(R.id.carbsAmount);
        tvCarbs.setText("Today's Carbs Amount: " + decfor.format(carbs) + "/" + carbsL + "g");
        TextView tvProtein = findViewById(R.id.proteinAmount);
        tvProtein.setText("Today's Protein Amount: " + decfor.format(protein) + "/" + proteinL + "g");
        TextView tvVitaminC = findViewById(R.id.vitamincAmount);
        tvVitaminC.setText("Today's Vitamin C Amount: " + decfor.format(vitaminc) + "/" + vitamincL + "mg");
        TextView tvFat = findViewById(R.id.fatAmount);
        tvFat.setText("Today's Calories Amount: " + decfor.format(fat) + "/" + fatL + "g");

        String mealandtype = "Meals taken for today: \n\n";
        for (int i = 0; i < todayMeal.size(); i++) {
            mealandtype += "Meal: " + todayMeal.get(i).getMeal() + "\nMeal Type: " + todayMeal.get(i).getMealType() + "\n\n";
        }

        TextView tvMeals = findViewById(R.id.todayMeals);
        tvMeals.setText(mealandtype);
    }
}