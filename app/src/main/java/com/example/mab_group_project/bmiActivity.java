package com.example.mab_group_project;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.text.DecimalFormat;

public class bmiActivity extends AppCompatActivity {

    EditText age, height, weight;
    RadioGroup genderGroup, activityGroup;
    RadioButton male, female, sedentary, light, moderate, high;
    Button calculateButton;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


        // Initialize variables
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        genderGroup = findViewById(R.id.genderGroup);
        activityGroup = findViewById(R.id.activityGroup);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        sedentary = findViewById(R.id.sedentary);
        light = findViewById(R.id.light);
        moderate = findViewById(R.id.moderate);
        high = findViewById(R.id.high);
        calculateButton = findViewById(R.id.calculateButton);
        sharedPrefs = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        decimalFormat = new DecimalFormat("#.##");
        Button backButton = findViewById(R.id.backButton);



        // Set previously saved user preferences
        age.setText(sharedPrefs.getString("age", ""));
        height.setText(sharedPrefs.getString("height", ""));
        weight.setText(sharedPrefs.getString("weight", ""));

        //for gender

        if (sharedPrefs.getString("gender", "").equals("Male")) {
            male.setChecked(true);
        }

        else if (sharedPrefs.getString("gender", "").equals("Female")) {
            female.setChecked(true);
        }

        //for activity level

        if (sharedPrefs.getString("activity", "").equals("Sedentary")) {
            sedentary.setChecked(true);
        }

        else if (sharedPrefs.getString("activity", "").equals("Light")) {
            light.setChecked(true);
        }

        else if (sharedPrefs.getString("activity", "").equals("Moderate")) {
            moderate.setChecked(true);
        }

        else if (sharedPrefs.getString("activity", "").equals("High")) {
            high.setChecked(true);
        }

        //back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity and return to the previous activity in the stack
            }
        });


        // Set OnClickListener for calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   // Get user input
                                                   int userAge = Integer.parseInt(age.getText().toString());
                                                   double userHeight = Double.parseDouble(height.getText().toString());
                                                   double userWeight = Double.parseDouble(weight.getText().toString());
                                                   String userGender = ((RadioButton)findViewById(genderGroup.getCheckedRadioButtonId())).getText().toString();
                                                   String userActivity = ((RadioButton)findViewById(activityGroup.getCheckedRadioButtonId())).getText().toString();


                                                   // Create an intent to start the new activity
                                                   Intent intent = new Intent(bmiActivity.this, ResultActivity.class);


                                                   // Calculate BMI
                                                   double userBMI = userWeight / ((userHeight / 100) * (userHeight / 100));
                                                   double healthyWeightLowerEnd = 18.5 * Math.pow(userHeight/100, 2);
                                                   double healthyWeightUpperEnd = 24.9 * Math.pow(userHeight/100, 2);
                                                   double weightNeedToNormal = 0.0;
                                                   DecimalFormat decimalFormat = new DecimalFormat("#.#"); // create a DecimalFormat object
                                                   String formattedWeight = decimalFormat.format(healthyWeightLowerEnd); // format the double value
                                                   String formattedWeightUpper = decimalFormat.format(healthyWeightUpperEnd); // format the double value
                                                   String bmiCategory = "";

                                                   if (userBMI <16.0) {
                                                       bmiCategory = "Underweight";
                                                       weightNeedToNormal= healthyWeightLowerEnd-userWeight;

                                                   }

                                                   else if (userBMI >= 16.0 && userBMI < 17.0) {
                                                       bmiCategory = "Moderate underweight";
                                                       weightNeedToNormal= healthyWeightLowerEnd-userWeight;
                                                   }

                                                   else if (userBMI >= 17.0 && userBMI < 18.5) {
                                                       bmiCategory = "Mildly underweight";
                                                       weightNeedToNormal= healthyWeightLowerEnd-userWeight;

                                                   }

                                                   else if (userBMI >= 18.5 && userBMI < 25.0){
                                                       bmiCategory = "Normal weight";
                                                       weightNeedToNormal= 0.0;

                                                   }

                                                   else if (userBMI >=25.0 && userBMI < 30.0){
                                                       bmiCategory = "Overweight";
                                                       weightNeedToNormal= healthyWeightUpperEnd-userWeight;

                                                   }


                                                   else if (userBMI >= 30.0 && userBMI < 35.0){
                                                       bmiCategory = "Obesity Class 1";
                                                       weightNeedToNormal= healthyWeightUpperEnd-userWeight;

                                                   }


                                                   else if (userBMI >= 35.5 && userBMI <40.0){
                                                       bmiCategory = "Obesity Class 2";
                                                       weightNeedToNormal= healthyWeightUpperEnd-userWeight;


                                                   }

                                                   else{
                                                       bmiCategory = "Obesity Class 3";
                                                       weightNeedToNormal= healthyWeightUpperEnd-userWeight;
                                                   }


                                                   // Calculate daily calorie requirements
                                                   double userCalories = 0;
                                                   if (userGender.equals("Male")) {
                                                       userCalories = (10 * userWeight) + (6.25 * userHeight) - (5 * userAge) + 5;
                                                   } else if (userGender.equals("Female")) {
                                                       userCalories = (10 * userWeight) + (6.25 * userHeight) - (5 * userAge) - 161;
                                                   }

                                                   if (userActivity.equals("Sedentary")) {
                                                       userCalories *= 1.2;
                                                   }

                                                   else if (userActivity.equals("Light")) {
                                                       userCalories *= 1.375;
                                                   }

                                                   else if (userActivity.equals("Moderate")) {
                                                       userCalories *= 1.55;
                                                   }

                                                   else if (userActivity.equals("High")) {
                                                       userCalories *= 1.725;
                                                   }

                                                   // Calculate calorie requirements for weight maintenance
                                                   double maintainCalories = userCalories;

                                                   // Calculate calorie requirements for weight loss (assuming a deficit of 500 calories per day)
                                                   double loseCalories = userCalories - 500;

                                                   // Calculate calorie requirements for weight gain (assuming a surplus of 500 calories per day)
                                                   double gainCalories = userCalories + 500;


                                                   // Pass the BMI and calorie results as extra data
                                                   intent.putExtra("BMI", decimalFormat.format(userBMI)+" kg/m²");
                                                   intent.putExtra("healthyWeightLowerEnd", formattedWeight ); // add the formatted weight to the intent
                                                   intent.putExtra("healthyWeightUpperEnd",   formattedWeightUpper + " kgs" ); // add the formatted weight to the intent
                                                   intent.putExtra("weightNeedToNormal",  weightNeedToNormal);


                                                   intent.putExtra("MaintainCalories", decimalFormat.format(maintainCalories)+" Calories/day");
                                                   intent.putExtra("GainCalories", decimalFormat.format(gainCalories)+" Calories/day");
                                                   intent.putExtra("LoseCalories", decimalFormat.format(loseCalories)+" Calories/day");


                                                   // Pass the BMI category as extra data
                                                   intent.putExtra("bmiCategory", bmiCategory);

                                                   // Start the new activity
                                                   startActivity(intent);

                                                   // Save user preferences
                                                   editor.putString("age", age.getText().toString());
                                                   editor.putString("height", height.getText().toString());
                                                   editor.putString("weight", weight.getText().toString());
                                                   editor.putString("gender", userGender);
                                                   editor.putString("activity", userActivity);
                                                   editor.apply();
                                               }


                                           }

        );
    }
}
