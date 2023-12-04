package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

public class Nutrient {
    @SerializedName("name")
    private  String name;
    @SerializedName("amount")
    private Double amount ;
    @SerializedName("unit")
    private  String unit;
    @SerializedName("percentOfDailyNeeds")
    private Double percentOfDailyNeeds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPercentOfDailyNeeds() {
        return percentOfDailyNeeds;
    }

    public void setPercentOfDailyNeeds(Double percentOfDailyNeeds) {
        this.percentOfDailyNeeds = percentOfDailyNeeds;
    }
}
