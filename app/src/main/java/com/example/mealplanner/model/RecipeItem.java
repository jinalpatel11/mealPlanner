package com.example.mealplanner.model;

// RecipeItem.java
public class RecipeItem {
    private int imageResource;
    private String text;

    public RecipeItem(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }
}

