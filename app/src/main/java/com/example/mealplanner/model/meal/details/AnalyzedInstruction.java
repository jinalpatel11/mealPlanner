package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class AnalyzedInstruction {
    @SerializedName("name")
    private String name;

    @SerializedName("steps")
    private List<Step> steps;

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    // Getter methods for each field


}

