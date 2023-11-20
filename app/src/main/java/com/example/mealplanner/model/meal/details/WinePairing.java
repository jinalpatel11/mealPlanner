package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public  class WinePairing {
    @SerializedName("pairedWines")
    private List<String> pairedWines;

    @SerializedName("pairingText")
    private String pairingText;

    @SerializedName("productMatches")
    private List<ProductMatch> productMatches;

    // Getter methods for each field

    public static class ProductMatch {
        @SerializedName("id")
        private int id;

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("price")
        private String price;

        @SerializedName("imageUrl")
        private String imageUrl;

        @SerializedName("averageRating")
        private double averageRating;

        @SerializedName("ratingCount")
        private double ratingCount;

        @SerializedName("score")
        private double score;

        @SerializedName("link")
        private String link;

        // Getter methods for each field
    }
}
