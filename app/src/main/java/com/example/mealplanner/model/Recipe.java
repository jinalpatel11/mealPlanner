package com.example.mealplanner.model;

import java.util.List;
// Recipe.java


public class Recipe {
    private int id;
    private String title;
    private String image;
    private int usedIngredientCount;
    private int missedIngredientCount;
    private List<Ingredient> missedIngredients;
    private List<Ingredient> usedIngredients;
    private List<Ingredient> unusedIngredients;
    private int likes;

    // Add getters and setters here

    public Recipe(int id, String title, String image, int usedIngredientCount, int missedIngredientCount,
                  List<Ingredient> missedIngredients, List<Ingredient> usedIngredients,
                  List<Ingredient> unusedIngredients, int likes) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.usedIngredientCount = usedIngredientCount;
        this.missedIngredientCount = missedIngredientCount;
        this.missedIngredients = missedIngredients;
        this.usedIngredients = usedIngredients;
        this.unusedIngredients = unusedIngredients;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public void setUsedIngredientCount(int usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    public List<Ingredient> getUsedIngredients() {
        return usedIngredients;
    }

    public void setUsedIngredients(List<Ingredient> usedIngredients) {
        this.usedIngredients = usedIngredients;
    }

    public List<Ingredient> getMissedIngredients() {
        return missedIngredients;
    }

    public void setMissedIngredients(List<Ingredient> missedIngredients) {
        this.missedIngredients = missedIngredients;
    }
}
