package com.example.mab_group_project;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Food {
    @SerializedName("fdcId")
    private long fdcId;
    @SerializedName("description")
    private String description;
    @SerializedName("foodNutrients")
    private FoodNutrient[] foodNutrients;

    public long getFdcId() {
        return fdcId;
    }

    public void setFdcId(long fdcId) {
        this.fdcId = fdcId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(FoodNutrient[] foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    public static class FoodNutrient {
        @SerializedName("amount")
        private float amount;

        @SerializedName("nutrient")
        public Nutrient nutrient;
        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }

    public static class Nutrient {
        private String name;
        private String unitName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }
    }
}
