package com.example.mealplanner.controller;

import android.util.Log;

import com.example.mealplanner.model.network.meal.ProductSearchResponse;
import com.example.mealplanner.network.SpoonacularApiService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpoonacularApiController {

    private final SpoonacularApiService apiService;
    private final String apiKey;

    public SpoonacularApiController(SpoonacularApiService apiService, String apiKey) {
        this.apiService = apiService;
        this.apiKey = apiKey;
    }

    public void searchProducts(Map<String, String> options, final ApiCallback<ProductSearchResponse> callback) {
        // Add apiKey to the options
        options.put("apiKey", apiKey);

        // Make the API call
        Call<ProductSearchResponse> call = apiService.searchProducts(apiKey, options);
        call.enqueue(new Callback<ProductSearchResponse>() {
            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResponse> call, Throwable t) {
                Log.e("SpoonacularApiController", "Error in API call", t);
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    // Define a callback interface to handle API responses
    public interface ApiCallback<T> {
        void onSuccess(T result);

        void onFailure(String errorMessage);
    }
}

