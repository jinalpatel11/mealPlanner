package com.example.mealplanner.view.meal;
// MealListActivity.java
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.network.meal.Product;
import com.example.mealplanner.model.network.meal.ProductSearchResponse;
import com.example.mealplanner.network.ApiClient;
import com.example.mealplanner.network.SpoonacularApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealListActivity extends AppCompatActivity {
    private  String API_KEY = getResources().getString(R.string.api_key);
    private SpoonacularApiService apiService;
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter; // Create a custom adapter for the RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);

        // Initialize Retrofit service
        apiService = ApiClient.getClient().create(SpoonacularApiService.class);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        mealAdapter = new MealAdapter(); // Create a custom adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mealAdapter);

        // Example usage to search for pizza products
        Map<String, String> options = new HashMap<>();
        options.put("query", "pizza");
        options.put("number", "2");

        makeApiRequest(API_KEY, options);
    }

    private void makeApiRequest(String apiKey, Map<String, String> options) {
        Call<ProductSearchResponse> call = apiService.searchProducts(apiKey, options);
        call.enqueue(new Callback<ProductSearchResponse>() {
            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                if (response.isSuccessful()) {
                    ProductSearchResponse productSearchResponse = response.body();
                    List<Product> products = productSearchResponse.getProducts();

                    // Update the adapter with the list of products
                    mealAdapter.setProducts(products);
                    mealAdapter.notifyDataSetChanged();
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(MealListActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResponse> call, Throwable t) {
                // Handle failure
                Toast.makeText(MealListActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
