package com.example.mealplanner.view.meal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityRecipeDetailsBinding;
import com.example.mealplanner.model.meal.details.AnalyzedInstruction;
import com.example.mealplanner.model.meal.details.ExtendedIngredient;
import com.example.mealplanner.model.meal.RecipeDetails;
import com.example.mealplanner.model.meal.details.Nutrient;
import com.example.mealplanner.model.meal.details.Nutrition;
import com.example.mealplanner.model.meal.details.Step;
import com.example.mealplanner.network.ApiClient;
import com.example.mealplanner.network.SpoonacularApiService;
import com.squareup.picasso.Picasso;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailsActivity extends AppCompatActivity {
    private SpoonacularApiService apiService;
    private ActivityRecipeDetailsBinding binding; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(" Activity","Start");
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
        Call<RecipeDetails> call = apiService.getRecipeInformation(recipeId, apiKey , true);
        call.enqueue(new Callback<RecipeDetails>() {
            @Override
            public void onResponse(Call<RecipeDetails> call, Response<RecipeDetails> response) {
                if (response.isSuccessful() && response.body() != null ) {
                    RecipeDetails recipeDetails = response.body();
                    // Update UI with the fetched recipe details
                    Log.e("RecipeDetailsActivity", "Successfully");

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

        // Set the summary text with HTML formatting
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.recipeSummaryTextView.setText(Html.fromHtml(recipeDetails.getSummary(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.recipeSummaryTextView.setText(Html.fromHtml(recipeDetails.getSummary()));
        }

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


        // Assuming you have a TableLayout in your XML layout file with the id "nutritionTable"
        // Assuming you have a TableLayout in your XML layout file with the ids "mainNutrientsTable" and "supplementalNutrientsTable"
        // Use binding to access views
        TableLayout mainNutrientsTable = binding.mainNutrientsTable;
        TableLayout supplementalNutrientsTable = binding.supplementalNutrientsTable;

        // Create header row for main nutrients table
        TableRow mainHeaderRow = new TableRow(this);
        mainHeaderRow.addView(createTextView("Nutrient", true));
        mainHeaderRow.addView(createTextView("Amount", true));
        mainHeaderRow.addView(createTextView("Percent of Daily Needs", true));
        mainNutrientsTable.addView(mainHeaderRow);

        // Create header row for supplemental nutrients table
        TableRow supplementalHeaderRow = new TableRow(this);
        supplementalHeaderRow.addView(createTextView("Nutrient", true));
        supplementalHeaderRow.addView(createTextView("Amount", true));
        supplementalNutrientsTable.addView(supplementalHeaderRow);

        // Separate nutrients into main and supplemental categories
        Nutrition nutrition = recipeDetails.getNutrition();
        List<Nutrient> nutrientList = nutrition.getNutrients();
        for (Nutrient nutrient : nutrientList) {
            TableRow dataRow;

            // Determine the table to add the nutrient based on its category
            if (isMainNutrient(nutrient.getName())) {
                dataRow = new TableRow(this);
                dataRow.addView(createTextView(nutrient.getName(), false));
                dataRow.addView(createTextView(String.valueOf(nutrient.getAmount()), false));
                dataRow.addView(createTextView(String.valueOf(nutrient.getPercentOfDailyNeeds()), false));
                Log.d("Jinal Main nutrient",nutrient.getName());
                mainNutrientsTable.addView(dataRow);
            } else {
                Log.d("Jinal Not Main nutrient",nutrient.getName());
                dataRow = new TableRow(this);
                dataRow.addView(createTextView(nutrient.getName(), false));
                dataRow.addView(createTextView(String.valueOf(nutrient.getAmount()), false));
                supplementalNutrientsTable.addView(dataRow);
            }
        }


    }

    // Add a method to load images using Picasso or Glide
    private void loadImage(String imageUrl) {
        // Example using Picasso (you need to add the Picasso dependency)
        Picasso.get().load(imageUrl).into(binding.recipeImageView);

        // Example using Glide (you need to add the Glide dependency)
        // Glide.with(this).load(imageUrl).into(binding.recipeImageView);
    }

    private TextView createTextView(String nutrientName, boolean isHeader) {
        TextView textView = new TextView(this);

        // Set layout parameters
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        textView.setLayoutParams(params);

        // Set visibility
        textView.setVisibility(View.VISIBLE);

        textView.setGravity(Gravity.CENTER);
        textView.setPadding(8, 8, 8, 8);
        Log.d("Jinal", "Creating TextView for: " + nutrientName + ", isHeader: " + isHeader);

        if (isHeader) {
            textView.setBackgroundResource(R.drawable.cell_header_background);
            textView.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            // Set different colors based on nutrient name
            int textColor = getColorForNutrient(nutrientName);
            textView.setTextColor(textColor);

            textView.setBackgroundResource(R.drawable.cell_background);
        }

        textView.setText(nutrientName); // Set the nutrient name as text

        return textView;
    }

    private int getColorForNutrient(String nutrientName) {
        int defaultColorResId = R.color.black;

        // Add conditions based on nutrient name
        if ("Calories".equals(nutrientName)) {
            Log.d("ColorDebug", "Selected color for Calories");
            return getResources().getColor(R.color.caloriesColor); // Set color dynamically
        } else if ("Protein".equals(nutrientName)) {
            Log.d("ColorDebug", "Selected color for Protein");
            return getResources().getColor(R.color.proteinColor); // Set color dynamically
        }

        // Default color if no specific condition is met
        Log.d("ColorDebug", "Selected default color");
        return getResources().getColor(defaultColorResId);
    }

    private boolean isMainNutrient(String nutrientName) {
        // Define the main nutrient names
        Set<String> mainNutrientNames = new HashSet<>(Arrays.asList(
                "Calories",
                "Fat",
                "Trans Fat",
                "Mono Unsaturated Fat",
                "Poly Unsaturated Fat",
                "Protein",
                "Cholesterol",
                "Carbohydrates",
                "Net Carbohydrates",
                "Alcohol",
                "Fiber",
                "Sugar"
                // Add more main nutrient names as needed
        ));

        return mainNutrientNames.contains(nutrientName);
    }
}
