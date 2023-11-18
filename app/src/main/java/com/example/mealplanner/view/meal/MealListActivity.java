package com.example.mealplanner.view.meal;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.model.network.meal.Product;
import com.example.mealplanner.model.network.meal.ProductSearchResponse;
import com.example.mealplanner.network.ApiClient;
import com.example.mealplanner.network.SpoonacularApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealListActivity extends AppCompatActivity {
    private static final String API_KEY = "dcdedec37a6142a4a44bac515bd77f51"; // Replace with your actual API key
     private SpoonacularApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);

        // Initialize Retrofit service
        apiService = ApiClient.getClient().create(SpoonacularApiService.class);
        // Example usage to search for pizza products
        Map<String, String> options = new HashMap<>();
        options.put("query", "pizza");
        options.put("number", "2");

        makeApiRequest(API_KEY, options);

    }

    private void makeApiRequest(String apiKey, Map<String, String> options) {
        Call<ProductSearchResponse> call = apiService.searchProducts(apiKey, options);

        Log.d("Request URL", call.request().url().toString());
        call.enqueue(new Callback<ProductSearchResponse>() {

            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                if (response.isSuccessful()) {
                    ProductSearchResponse productSearchResponse = response.body();
                    List<Product> products = productSearchResponse.getProducts();

                    // Handle the list of products as needed
                    for (Product product : products) {
                        // Do something with each product (e.g., display in a list)
                        Log.d("Product", "ID: " + product.getId() + ", Title: " + product.getTitle());
                    }
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
