package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Step {
    @SerializedName("number")
    private int number;

    @SerializedName("step")
    private String step;

    @SerializedName("ingredients")
    private List<Ingredient> ingredients;

    @SerializedName("equipment")
    private List<Equipment> equipment;

    @SerializedName("length")
    private Length length;

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // Getter methods for each field



}
