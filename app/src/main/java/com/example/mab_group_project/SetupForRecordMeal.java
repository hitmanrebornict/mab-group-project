package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetupForRecordMeal extends AppCompatActivity {

    EditText calorieInput, proteinInput, fatInput, vitamincInput, carbsInput;
    Button btnProceed;

    //setup: prompts user to enter daily amount of calories, protein, fat, vitamin c
    //then: saves into a SharedPreferences file for RecordMeals activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_for_record_meal);

        //set edit texts and their text changed listener
        calorieInput = findViewById(R.id.etInputCaloriesAmount);
        proteinInput = findViewById(R.id.etInputProteinAmount);
        fatInput = findViewById(R.id.etInputFatAmount);
        vitamincInput = findViewById(R.id.etInputVitaminCAmount);
        carbsInput = findViewById(R.id.etInputCarbsAmount);

        calorieInput.addTextChangedListener(new TextValidator(calorieInput));
        proteinInput.addTextChangedListener(new TextValidator(proteinInput));
        fatInput.addTextChangedListener(new TextValidator(fatInput));
        vitamincInput.addTextChangedListener(new TextValidator(vitamincInput));

        //set onclicklistener to proceed button to save nutrients to be used in RecordMeal
        btnProceed = findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeNutrients();
            }
        });
    }

    //method to store nutrients inputted by user into shared preferences
    public void storeNutrients() {

        int calories = Integer.parseInt(calorieInput.getText().toString());
        int protein = Integer.parseInt(proteinInput.getText().toString());
        int fat = Integer.parseInt(fatInput.getText().toString());
        int vitaminc = Integer.parseInt(vitamincInput.getText().toString());
        int carbs = Integer.parseInt(carbsInput.getText().toString());

        SharedPreferences sp = getSharedPreferences("dailyNutrients", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Calories", calories);
        editor.putInt("Protein", protein);
        editor.putInt("Fat", fat);
        editor.putInt("Vitamin_C", vitaminc);
        editor.putInt("Carbs", carbs);
        editor.apply();

        Log.i("SharedPreferences created: ", "sharedpreferences sp created");
    }

    //validate user text so that nutrients do not <= 0
    public class TextValidator implements TextWatcher {

        private final EditText et;

        public TextValidator(EditText e) {
            this.et = e;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { return;}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { return; }

        @Override
        public void afterTextChanged(Editable s) {
            String text = et.getText().toString();
            if (text.equals("0")) et.setError("Value cannot be 0");
            else et.setError(null);
        }
    }
}