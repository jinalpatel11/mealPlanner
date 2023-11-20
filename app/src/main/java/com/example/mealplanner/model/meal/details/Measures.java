package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

public  class Measures {
    @SerializedName("us")
    private Us us;

    @SerializedName("metric")
    private Metric metric;

    // Getter methods for each field

    public static class Us {
        @SerializedName("amount")
        private double amount;

        @SerializedName("unitShort")
        private String unitShort;

        @SerializedName("unitLong")
        private String unitLong;

        // Getter methods for each field
    }

    public static class Metric {
        @SerializedName("amount")
        private double amount;

        @SerializedName("unitShort")
        private String unitShort;

        @SerializedName("unitLong")
        private String unitLong;

        // Getter methods for each field
    }
}
