package com.example.mealplanner.view.meal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.TextView;

import com.example.mealplanner.databinding.ActivityRecipeDetailsBinding;
import com.example.mealplanner.model.meal.details.AnalyzedInstruction;
import com.example.mealplanner.model.meal.details.ExtendedIngredient;
import com.example.mealplanner.model.meal.RecipeDetails;
import com.example.mealplanner.model.meal.details.Step;
import com.example.mealplanner.network.ApiClient;
import com.example.mealplanner.network.SpoonacularApiService;
import com.squareup.picasso.Picasso;


import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailsActivity extends AppCompatActivity {
    private SpoonacularApiService apiService;
    private ActivityRecipeDetailsBinding binding; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using view binding
        binding = ActivityRecipeDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiService = ApiClient.getClient().create(SpoonacularApiService.class);

        // Retrieve the recipe details from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("recipe_id")) {
            int recipeId = intent.getIntExtra("recipe_id", 0);

            // Use the recipeId to fetch additional details and update the UI
            // Example: Fetch recipe details by ID
            fetchRecipeDetailsById(recipeId);
        }
    }

    private void fetchRecipeDetailsById(int recipeId) {
        Log.d("RecipeID", String.valueOf(recipeId));
        String apiKey = "dcdedec37a6142a4a44bac515bd77f51"; // Replace with your Spoonacular API key

        // Example: Fetch recipe details by ID
        Call<RecipeDetails> call = apiService.getRecipeInformation(recipeId, apiKey);
        call.enqueue(new Callback<RecipeDetails>() {
            @Override
            public void onResponse(Call<RecipeDetails> call, Response<RecipeDetails> response) {
                if (response.isSuccessful() && response.body() != null ) {
                    RecipeDetails recipeDetails = response.body();
                    // Update UI with the fetched recipe details
                    updateUI(recipeDetails);
                } else {
                    Log.e("RecipeDetailsActivity", "Error fetching recipe details");
                }
            }

            @Override
            public void onFailure(Call<RecipeDetails> call, Throwable t) {
                Log.e("RecipeDetailsActivity", "Error fetching recipe details: " + t.getMessage());
            }
        });
    }

    private void updateUI(RecipeDetails recipeDetails) {
        // Update your UI with the fetched recipe details
        binding.recipeTitleTextView.setText(recipeDetails.getTitle());

        // Load image using a library like Picasso or Glide
        // Assuming you have a method loadImage for loading images
        loadImage(recipeDetails.getImage());

        binding.recipeSummaryTextView.setText(recipeDetails.getSummary());

        // Set Ingredients
        List<ExtendedIngredient> ingredients = recipeDetails.getExtendedIngredients();
        for (ExtendedIngredient ingredient : ingredients) {
            TextView ingredientTextView = new TextView(this);
            ingredientTextView.setText(String.format(Locale.getDefault(), "%s %s", ingredient.getAmount(), ingredient.getName()));
            binding.ingredientsLayout.addView(ingredientTextView);
        }

        // Set Instructions
        StringBuilder instructions = new StringBuilder();
        List<AnalyzedInstruction> analyzedInstructions = recipeDetails.getAnalyzedInstructions();
        for (AnalyzedInstruction instruction : analyzedInstructions) {
            for (Step step : instruction.getSteps()) {
                instructions.append(String.format(Locale.getDefault(), "%d. %s\n", step.getNumber(), step.getStep()));
            }
        }
        binding.instructionsTextView.setText(instructions.toString());
    }

    // Add a method to load images using Picasso or Glide
    private void loadImage(String imageUrl) {
        // Example using Picasso (you need to add the Picasso dependency)
        Picasso.get().load(imageUrl).into(binding.recipeImageView);

        // Example using Glide (you need to add the Glide dependency)
        // Glide.with(this).load(imageUrl).into(binding.recipeImageView);
    }

}
