package com.example.mealplanner.model;


import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecipeSearchComplexResponse {

    @SerializedName("offset")
    private int offset;

    @SerializedName("number")
    private int number;

    @SerializedName("results")
    private List<RecipeItemComplexSearch> results;

    @SerializedName("totalResults")
    private int totalResults;

    // Getters and setters

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<RecipeItemComplexSearch> getResults() {
        return results;
    }

    public void setResults(List<RecipeItemComplexSearch> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}

