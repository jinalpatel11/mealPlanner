package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

public class CaloricBreakdown {
    @SerializedName("percentProtein")
    private  Double percentProtein;
    @SerializedName("percentFat")
    private  Double percentFat;
    @SerializedName("percentCarbs")
    private  Double percentCarbs;
}
