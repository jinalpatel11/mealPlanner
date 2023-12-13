package com.example.mealplanner.view.meal;


// ViewAllRecipesActivity.java

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityMyProfileBinding;
import com.example.mealplanner.databinding.ActivityViewAllRecipesBinding;
import com.example.mealplanner.model.RecipeItem;
import com.example.mealplanner.model.RecipeItemComplexSearch;
import com.example.mealplanner.model.RecipeSearchComplexResponse;
import com.example.mealplanner.model.meal.Recipe;
import com.example.mealplanner.model.network.meal.ProductSearchResponse;
import com.example.mealplanner.network.ApiClient;
import com.example.mealplanner.network.SpoonacularApiService;
import com.example.mealplanner.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllRecipesActivity extends AppCompatActivity  implements RecipeTitleAdapter.RecipeTitleClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityViewAllRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Retrofit service
        apiService = ApiClient.getClient().create(SpoonacularApiService.class);


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
       RecipeTitleAdapter recipesAdapter = new RecipeTitleAdapter(recipeTitles, this);

        recipesRecyclerView.setAdapter(recipesAdapter);

        EditText edtSearchRecipes = findViewById(R.id.edtSearchRecipes);
        ImageButton cancelButton = findViewById(R.id.cancelButton);

        
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the text in the EditText when the cancel button is clicked
                edtSearchRecipes.setText("");
            }
        });

        edtSearchRecipes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed, but required to implement TextWatcher
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Check if the EditText has text and toggle the cancel button's visibility
                if (charSequence.length() > 0) {
                    cancelButton.setVisibility(View.VISIBLE);
                } else {
                    cancelButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not needed, but required to implement TextWatcher
            }
        });

    }

    private ActivityViewAllRecipesBinding binding;

    private SpoonacularApiService apiService;

    @Override
    public void onRecipeTitleClicked(String title) {
        // Handle the click event for the recipe title
        // Call your API here using the clicked title
        // Example: makeApiCall(title);

        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        // RecipeCard RecyclerView1


            LinearLayoutManager cardLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
          binding.recipeCardsRecyclerView1.setLayoutManager(cardLayoutManager1);
            fetchRecipesByIngredients(binding.recipeCardsRecyclerView1, title);


    }

    private void fetchRecipesByIngredients(RecyclerView recipeCardsRecyclerView1 ,String title) {
        // Call the API to get recipes by ingredients
        String apiKey  = getResources().getString(R.string.api_key);
        // Assuming you have the Retrofit instance and SpoonacularApiService set up

        // Create a map for additional query parameters
        Map<String, String> options = new HashMap<>();
        options.put("query", title);

        // Create a Call object using the complexSearch method
        Call<RecipeSearchComplexResponse> call = apiService.complexSearch(
                apiKey,
                options
        );


        // Enqueue the call asynchronously

        call.enqueue(new Callback<RecipeSearchComplexResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchComplexResponse> call, Response<RecipeSearchComplexResponse> response) {
                int statusCode = response.code();
                Log.d("ViewAllActivity", "Response Code: " + statusCode);
                if (response.isSuccessful()) {
                    RecipeSearchComplexResponse recipes = response.body();
                    // Log the response body
                    Log.d("ViewAllActivity", "Response Body: " + recipes);

                    if (recipes != null) {
                        // Process the recipes
                        updateRecipesCardAdapter(recipes , recipeCardsRecyclerView1);
                    } else {
                        // Handle null response
                        Log.e("ViewAllActivity", "Null response body");
                    }

                } else {
                    // Handle API error based on the status code
                    Log.e("ViewAllActivity", "API error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchComplexResponse> call, Throwable t) {
                // Handle API call failure
                Log.e("ViewAllActivity", "API call failed: " + t.getMessage());
            }
        });
    }




    private void updateRecipesCardAdapter(RecipeSearchComplexResponse recipes, RecyclerView recipeCardsRecyclerView) {
        // Create a list to hold the single recipe
        List<Recipe> recipesList = new ArrayList<>();

        recipesList = convertToRecipeList(recipes.getResults());

        // Update the RecyclerView adapter with the new list of recipes
        RecipesCardAdapter recipesCardAdapter = new RecipesCardAdapter(this, recipesList);
        recipeCardsRecyclerView.setAdapter(recipesCardAdapter);
    }

    private List<Recipe> convertToRecipeList(List<RecipeItemComplexSearch> recipeItemList) {
        List<Recipe> recipeList = new ArrayList<>();

        for (RecipeItemComplexSearch recipeItem : recipeItemList) {
            Recipe convertedRecipe = convertToRecipe(recipeItem);
            recipeList.add(convertedRecipe);
        }

        return recipeList;
    }

    private Recipe convertToRecipe(RecipeItemComplexSearch recipeItem) {
        Recipe recipe = new Recipe();

        // Set corresponding fields
        recipe.setId(recipeItem.getId());
        recipe.setTitle(recipeItem.getTitle());
        recipe.setImage(recipeItem.getImage());


        return recipe;
    }




}
