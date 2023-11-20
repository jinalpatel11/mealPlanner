package com.example.mealplanner.network;

import com.example.mealplanner.model.Recipe;
import com.example.mealplanner.model.network.ApiResponse;
import com.example.mealplanner.model.network.meal.Meal;
import com.example.mealplanner.model.network.meal.ProductSearchResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
// SpoonacularApiService.java
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SpoonacularApiService {
    @GET("/food/products/search")
    Call<ProductSearchResponse> searchProducts(@Query("apiKey") String apiKey, @QueryMap Map<String, String> options);

   // @GET("/recipes/findByIngredients")
   // Call<List<Recipe>> getRecipesByIngredients(@Query("apiKey") String apiKey, @QueryMap Map<String, String> options);

    // (Add this method to your existing interface)
    @GET("/recipes/findByIngredients")
    Call<List<Recipe>> getRecipesByIngredients(
            @Query("apiKey") String apiKey,
            @Query("ingredients") String ingredients,
            @Query("number") int number
    );
}



