package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

public class Propertie {
    @SerializedName("name")
    private  String name;
    @SerializedName("amount")
    private  Double amount;
    @SerializedName("unit")
    private String unit;
}
