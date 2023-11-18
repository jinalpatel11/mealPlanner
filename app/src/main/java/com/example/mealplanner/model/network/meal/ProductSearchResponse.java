package com.example.mealplanner.model.network.meal;

// ProductSearchResponse.java
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProductSearchResponse {
    @SerializedName("products")
    private List<Product> products;

    @SerializedName("totalProducts")
    private int totalProducts;

    @SerializedName("type")
    private String type;

    @SerializedName("offset")
    private int offset;

    @SerializedName("number")
    private int number;

    public List<Product> getProducts() {
        return products;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public String getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public int getNumber() {
        return number;
    }
}

