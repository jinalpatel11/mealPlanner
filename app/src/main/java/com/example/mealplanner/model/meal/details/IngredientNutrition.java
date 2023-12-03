package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientNutrition {
    @SerializedName("id")
    private  int id;
    @SerializedName("name")
    private String name;
    @SerializedName("amount")
    private Double amount;
    @SerializedName("unit")
    private String unit;
    @SerializedName("nutrients")
    private List<Nutrient> nutrients;
}
