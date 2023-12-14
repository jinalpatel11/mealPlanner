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


    private  String selectedCategory;

    private ActivityViewAllRecipesBinding binding;

    private SpoonacularApiService apiService;

    private RecipeTitleAdapter recipesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityViewAllRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Retrofit service
        apiService = ApiClient.getClient().create(SpoonacularApiService.class);

        // Retrieve the selected category from the intent
        selectedCategory = getIntent().getStringExtra("category");


        // Recipes RecyclerView
        RecyclerView recipesRecyclerView = findViewById(R.id.recipesTitleRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recipesRecyclerView.setLayoutManager(layoutManager);

        // Create a list of static recipe titles
        List<String> recipeTitles = new ArrayList<>();
        recipeTitles.add("Quick & Easy");
        recipeTitles.add("Breakfast and Brunch");
        recipeTitles.add("Lunch");
        recipeTitles.add("Main Course");
        recipeTitles.add("Beverage");
        recipeTitles.add("Salad");
        recipeTitles.add("Snacks");
        recipeTitles.add("Dessert");
        recipeTitles.add("Side Course");
        recipeTitles.add("Vegetarian");
        recipeTitles.add("Paleo");
        recipeTitles.add("Gluten Free");
        recipeTitles.add("Fasting Friendly");

        // Set up the adapter for the RecyclerView
        recipesAdapter = new RecipeTitleAdapter(recipeTitles, this);
        recipesRecyclerView.setAdapter(recipesAdapter);

        // Auto-select the item based on the category
        autoSelectCategory();



        EditText edtSearchRecipes = findViewById(R.id.edtSearchRecipes);
        ImageButton cancelButton = findViewById(R.id.cancelButton);


        setupCancelButton();

        // Set up the click listener for the search button
       binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the method to fetch recipes based on the search query
                String searchQuery = edtSearchRecipes.getText().toString();
                if (!searchQuery.isEmpty()) {
                    Toast.makeText(ViewAllRecipesActivity.this, searchQuery, Toast.LENGTH_SHORT).show();

                    fetchRecipesByIngredients(binding.recipeCardsRecyclerView1,searchQuery,0);
                    fetchRecipesByIngredients(binding.recipeCardsRecyclerView2,searchQuery,1);

                    fetchRecipesByIngredients(binding.recipeCardsRecyclerView3,searchQuery,2);

                } else {
                    // Handle the case where the search query is empty
                    Toast.makeText(ViewAllRecipesActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }
            }
        });


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

    private void autoSelectCategory() {
        if (selectedCategory == null) {
         selectedCategory = "Quick & Easy";
        }

        int selectedIndex = recipesAdapter.getPositionForCategory(selectedCategory);
        if (selectedIndex != -1) {
            recipesAdapter.setSelectedIndex(selectedIndex);
            onRecipeTitleClicked(selectedCategory);
        }
    }


    @Override
    public void onRecipeTitleClicked(String title) {

        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        // RecipeCard RecyclerView1
            LinearLayoutManager cardLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            binding.recipeCardsRecyclerView1.setLayoutManager(cardLayoutManager1);
            fetchRecipesByIngredients(binding.recipeCardsRecyclerView1, title,0);


        // RecipeCard RecyclerView2
        LinearLayoutManager cardLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recipeCardsRecyclerView2.setLayoutManager(cardLayoutManager2);
        fetchRecipesByIngredients(binding.recipeCardsRecyclerView2, title,1);

        // RecipeCard RecyclerView3
        LinearLayoutManager cardLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recipeCardsRecyclerView3.setLayoutManager(cardLayoutManager3);
        fetchRecipesByIngredients(binding.recipeCardsRecyclerView3, title,2);

    }

    private void fetchRecipesByIngredients(RecyclerView recipeCardsRecyclerView1 ,String title,int offSet) {
        // Call the API to get recipes by ingredients
        String apiKey  = getResources().getString(R.string.api_key);
        // Assuming you have the Retrofit instance and SpoonacularApiService set up

        // Create a map for additional query parameters
        Map<String, String> options = new HashMap<>();
        options.put("query", title);
        options.put("offset", String.valueOf(offSet));
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


    private  void setupCancelButton(){
        // Assuming you have a button in your layout with the ID "backButton"
        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call onBackPressed to simulate the back button press
                onBackPressed();
            }
        });
    }


}
