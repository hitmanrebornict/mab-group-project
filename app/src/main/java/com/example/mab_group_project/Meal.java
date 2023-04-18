package com.example.mab_group_project;

public class Meal {
    private String meal;
    private String mealType;
    private String dateTime;
    private float calories, protein, fat, vitaminc, carbs;

    public Meal(String meal, String mealType, String dateTime, float calories, float protein, float fat, float vitaminc, float carbs) {
        this.meal = meal;
        this.mealType = mealType;
        this.dateTime = dateTime;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.vitaminc = vitaminc;
        this.carbs = carbs;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public void setVitaminc(float vitaminc) {
        this.vitaminc = vitaminc;
    }

    public String getMeal() {
        return meal;
    }

    public String getMealType() {
        return mealType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public float getCalories() {
        return calories;
    }

    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }

    public float getVitaminc() {
        return vitaminc;
    }
}
