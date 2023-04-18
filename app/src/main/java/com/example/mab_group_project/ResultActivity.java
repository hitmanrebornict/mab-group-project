package com.example.mab_group_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        // Get the extra data from the intent
        String bmi = getIntent().getStringExtra("BMI");
        String bmiCategory = getIntent().getStringExtra("bmiCategory");
        String healthyWeightLowerEnd = getIntent().getStringExtra("healthyWeightLowerEnd");
        String healthyWeightUpperEnd = getIntent().getStringExtra("healthyWeightUpperEnd");
        // Retrieve the weightNeedToNormal value from the intent
        double weightNeedToNormal = getIntent().getDoubleExtra("weightNeedToNormal", 0.0);
        String weightToLoseString = Double.toString(weightNeedToNormal);

        //format the weight to normal
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedDouble = decimalFormat.format(weightNeedToNormal);



        String maintainCalories = getIntent().getStringExtra("MaintainCalories");
        String gainCalories = getIntent().getStringExtra("GainCalories");
        String loseCalories = getIntent().getStringExtra("LoseCalories");

        // Display the results in TextViews
        TextView bmiResultTextView = findViewById(R.id.bmiResult);
        TextView bmiCategoryTextView = findViewById(R.id.bmiCategory);
        TextView healthyWeightLowerEndTextView = findViewById(R.id.LowerEnd);
        TextView healthyWeightUpperEndTextView = findViewById(R.id.UpperEnd);
        TextView weightNeedToNormalTextView = findViewById(R.id.weightToNormal);

        TextView maintainCalorieResultTextView = findViewById(R.id.maintainCalories);
        TextView gainCalorieResultTextView = findViewById(R.id.gainCalories);
        TextView loseCalorieResultTextView = findViewById(R.id.loseCalories);


        if(bmiCategory.equals("Underweight")) {
            bmiCategoryTextView.setTextColor(getResources().getColor(R.color.red));
        } else if(bmiCategory.equals("Moderate underweight")) {
            bmiCategoryTextView.setTextColor(getResources().getColor(R.color.pink));
        } else if(bmiCategory.equals("Mildly underweight")) {
            bmiCategoryTextView.setTextColor(getResources().getColor(R.color.yellow));
        } else if(bmiCategory.equals("Normal weight")) {
            bmiCategoryTextView.setTextColor(getResources().getColor(R.color.green));
        } else if(bmiCategory.equals("Overweight")) {
            bmiCategoryTextView.setTextColor(getResources().getColor(R.color.yellow));
        } else if(bmiCategory.equals("Obesity Class 1")) {
            bmiCategoryTextView.setTextColor(getResources().getColor(R.color.pink));
        } else if(bmiCategory.equals("Obesity Class 2")) {
            bmiCategoryTextView.setTextColor(getResources().getColor(R.color.red));
        } else if(bmiCategory.equals("Obesity Class 3")) {
            bmiCategoryTextView.setTextColor(getResources().getColor(R.color.dark_red));
        }

        bmiResultTextView.setText(bmi);
        healthyWeightLowerEndTextView.setText(healthyWeightLowerEnd);
        healthyWeightUpperEndTextView.setText(healthyWeightUpperEnd);
        weightNeedToNormalTextView.setText(formattedDouble + " kgs");
        bmiCategoryTextView.setText(bmiCategory);

        maintainCalorieResultTextView.setText(maintainCalories);
        gainCalorieResultTextView.setText(gainCalories);
        loseCalorieResultTextView.setText(loseCalories);


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity and return to the previous activity in the stack
            }
        });


    }
}