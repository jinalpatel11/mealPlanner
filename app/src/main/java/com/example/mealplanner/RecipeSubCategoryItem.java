package com.example.mealplanner;
import com.example.mealplanner.model.meal.Recipe;

import java.util.List;

public class RecipeSubCategoryItem {
    private String title;
    private List<Recipe> recipes;

    public RecipeSubCategoryItem(String title, List<Recipe> recipes) {
        this.title = title;
        this.recipes = recipes;
    }

    public String getTitle() {
        return title;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}

