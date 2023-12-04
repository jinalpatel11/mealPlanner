package com.example.mealplanner.view.meal;


// ViewAllRecipesActivity.java

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.RecipeItem;

import java.util.ArrayList;
import java.util.List;

public class ViewAllRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_recipes);

        // Recipes RecyclerView
        RecyclerView recipesRecyclerView = findViewById(R.id.recipesTitleRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recipesRecyclerView.setLayoutManager(layoutManager);

        // Create a list of static recipe titles
        List<String> recipeTitles = new ArrayList<>();
        recipeTitles.add("All Premium Recipes");
        recipeTitles.add("Quick & Easy Recipes");
        recipeTitles.add("Main Courses");
        recipeTitles.add("Breakfasts");
        recipeTitles.add("Lunch");
        recipeTitles.add("Beverages");
        recipeTitles.add("Salads");
        recipeTitles.add("Snacks");
        recipeTitles.add("Desserts");
        recipeTitles.add("Sides");
        recipeTitles.add("Vegetarian");
        recipeTitles.add("Paleo");
        recipeTitles.add("Gluten Free");
        recipeTitles.add("Fasting Friendly");


        // Set up the adapter for the RecyclerView
        RecipeTitleAdapter recipesAdapter = new RecipeTitleAdapter(recipeTitles);
        recipesRecyclerView.setAdapter(recipesAdapter);
    }
}
