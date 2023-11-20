package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ExtendedIngredient {
    @SerializedName("id")
    private int id;

    @SerializedName("aisle")
    private String aisle;

    @SerializedName("image")
    private String image;

    @SerializedName("consistency")
    private String consistency;

    @SerializedName("name")
    private String name;

    @SerializedName("nameClean")
    private String nameClean;

    @SerializedName("original")
    private String original;

    @SerializedName("originalName")
    private String originalName;

    @SerializedName("amount")
    private double amount;

    @SerializedName("unit")
    private String unit;

    @SerializedName("meta")
    private List<String> meta;

    @SerializedName("measures")
    private Measures measures;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter methods for each field


}


