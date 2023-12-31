package com.example.mealplanner.network;

import com.example.mealplanner.model.RecipeSearchComplexResponse;
import com.example.mealplanner.model.meal.Recipe;
import com.example.mealplanner.model.meal.RecipeDetails;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
// SpoonacularApiService.java
import retrofit2.http.Query;

public interface SpoonacularApiService {


    @GET("/recipes/findByIngredients")
    Call<List<Recipe>> getRecipesByIngredients(
            @Query("apiKey") String apiKey,
            @Query("ingredients") String ingredients,
            @Query("number") int number
    );


    @GET("/recipes/{recipeId}/information")
    Call<RecipeDetails> getRecipeInformation(
            @Path("recipeId") int recipeId,
            @Query("apiKey") String apiKey,
            @Query("includeNutrition") Boolean includeNutrition
    );

    @GET("/recipes/complexSearch")
    Call<RecipeSearchComplexResponse> complexSearch(@Query("apiKey") String apiKey, @QueryMap Map<String, String> options);




}



