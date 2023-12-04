package com.example.mealplanner.view.home;
// HomeActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityHomeBinding;
import com.example.mealplanner.model.meal.Recipe;
import com.example.mealplanner.model.RecipeItem;
import com.example.mealplanner.network.ApiClient;
import com.example.mealplanner.network.SpoonacularApiService;
import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.meal.MealListActivity;
import com.example.mealplanner.view.meal.RecipesAdapter;
import com.example.mealplanner.view.meal.RecipesCardAdapter;

import com.example.mealplanner.view.meal.ViewAllRecipesActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private GoogleSignInClient googleSignInClient;

    private SpoonacularApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve the email address from the Intent
        setupUserInfo();
        // Configure Google Sign-In
        setupGoogleSignIn();

        // Set up click listener for Logout Button using View Binding
        setupLogoutButton();

        setupViweAllButton();

        // Recipes RecyclerView
        RecyclerView recipesRecyclerView = findViewById(R.id.recipesRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recipesRecyclerView.setLayoutManager(layoutManager);

        // In your activity or fragment
        List<RecipeItem> recipeItems = new ArrayList<>();
        recipeItems.add(new RecipeItem(R.drawable.drink, "Drink"));
        recipeItems.add(new RecipeItem(R.drawable.drink, "Recipe 2"));
        recipeItems.add(new RecipeItem(R.drawable.drink, "Recipe 3"));
        recipeItems.add(new RecipeItem(R.drawable.drink, "Recipe 2"));
        recipeItems.add(new RecipeItem(R.drawable.drink, "Recipe 3"));

        // Set up the adapter for the RecyclerView
        RecipesAdapter recipesAdapter = new RecipesAdapter(recipeItems);
        recipesRecyclerView.setAdapter(recipesAdapter);



        // Initialize Retrofit service
        apiService = ApiClient.getClient().create(SpoonacularApiService.class);


        // RecipeCard RecyclerView1
        RecyclerView recipeCardsRecyclerView1 = findViewById(R.id.recipeCardsRecyclerView1);
        LinearLayoutManager cardLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recipeCardsRecyclerView1.setLayoutManager(cardLayoutManager1);
        fetchRecipesByIngredients(recipeCardsRecyclerView1 , "lunch");

        // RecipeCard RecyclerView2
        RecyclerView recipeCardsRecyclerView2 = findViewById(R.id.recipeCardsRecyclerView2);
        LinearLayoutManager cardLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recipeCardsRecyclerView2.setLayoutManager(cardLayoutManager2);
        fetchRecipesByIngredients(recipeCardsRecyclerView2 , "chocolate");

        // RecipeCard RecyclerView3
        RecyclerView recipeCardsRecyclerView3 = findViewById(R.id.recipeCardsRecyclerView3);
        LinearLayoutManager cardLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recipeCardsRecyclerView3.setLayoutManager(cardLayoutManager3);
        fetchRecipesByIngredients(recipeCardsRecyclerView3 , "snack");



    }

    private void setupUserInfo() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USER_EMAIL")) {
            String userEmail = intent.getStringExtra("USER_EMAIL");
            String userInitial = extractInitial(userEmail);
            binding.emailTextView.setText(userInitial);
        }
    }
    private void signOut() {
        // Sign out from Google Sign-In
        googleSignInClient.signOut().addOnCompleteListener(this,
                task -> {
                    // Optional: Perform any additional sign-out actions
                });
    }

    private void navigateToLogin() {
        // Navigate to the LoginActivity and finish the current activity
        Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
    private void navigateToMealList() {
        // Navigate to the MealListActivity and finish the current activity
        Intent loginIntent = new Intent(HomeActivity.this, MealListActivity.class);
        startActivity(loginIntent);

    }

    // Function to extract the initial from the email address
    private String extractInitial(String email) {
        // Check if the email is not null and contains the "@" symbol
        if (email != null && email.contains("@")) {
            // Extract the part before the "@" symbol
            String[] parts = email.split("@");
            if (parts.length > 0) {
                // Get the first part (username) and extract the initial
                String username = parts[0];
                if (!username.isEmpty()) {
                    // Return the first character as the initial
                    return String.valueOf(username.charAt(0));
                }
            }
        }
        // Return a default value or handle the case where the email format is unexpected
        return "";
    }

    private void setupGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void setupLogoutButton() {
        binding.logoutButton.setOnClickListener(view -> {
            // Handle logout action
            signOut();// Sign out from Google Sign-In
            navigateToLogin(); // Navigate to the LoginActivity and finish the current activity
        });

    }


    private void setupViweAllButton() {
        // Inside your onCreate method in HomeActivity.java
        TextView viewAllTextView = findViewById(R.id.ViewAllText);

        viewAllTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, navigate to ViewAllActivity
                Intent viewAllIntent = new Intent(HomeActivity.this, ViewAllRecipesActivity.class);
                startActivity(viewAllIntent);
            }

        });

    }

    private void fetchRecipesByIngredients(RecyclerView recipeCardsRecyclerView1 ,String ingredients) {
        // Call the API to get recipes by ingredients
        String apiKey = "dcdedec37a6142a4a44bac515bd77f51"; // Replace with your actual API key

        int number = 10;

        Call<List<Recipe>> call = apiService.getRecipesByIngredients(apiKey, ingredients, number);

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                int statusCode = response.code();
                Log.d("HomeActivityAPI", "Response Code: " + statusCode);

                if (response.isSuccessful()) {
                    List<Recipe> recipes = response.body();
                    // Log the response body
                    Log.d("HomeActivity", "Response Body: " + recipes);

                    if (recipes != null) {
                        // Process the recipes
                        updateRecipesCardAdapter(recipes , recipeCardsRecyclerView1);
                    } else {
                        // Handle null response
                        Log.e("HomeActivity", "Null response body");
                    }

                } else {
                    // Handle API error based on the status code
                    Log.e("HomeActivity", "API error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                // Handle API call failure
                Log.e("HomeActivity", "API call failed: " + t.getMessage());
            }
        });
    }

    private void updateRecipesCardAdapter(List<Recipe> recipes ,RecyclerView recipeCardsRecyclerView) {
        // Update the RecyclerView adapter with the new list of recipes
        RecipesCardAdapter recipesCardAdapter = new RecipesCardAdapter(HomeActivity.this, recipes);
        recipeCardsRecyclerView.setAdapter(recipesCardAdapter);
    }



}
