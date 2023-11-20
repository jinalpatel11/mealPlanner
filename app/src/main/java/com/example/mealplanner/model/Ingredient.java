package com.example.mealplanner.model;

// Ingredient.java

import java.util.List;

public class Ingredient {
    private int id;
    private double amount;
    private String unit;
    private String unitLong;
    private String unitShort;
    private String aisle;
    private String name;
    private String original;
    private String originalName;
    private List<String> meta;
    private String image;

    // Add getters and setters here

    public Ingredient(int id, double amount, String unit, String unitLong, String unitShort, String aisle,
                      String name, String original, String originalName, List<String> meta, String image) {
        this.id = id;
        this.amount = amount;
        this.unit = unit;
        this.unitLong = unitLong;
        this.unitShort = unitShort;
        this.aisle = aisle;
        this.name = name;
        this.original = original;
        this.originalName = originalName;
        this.meta = meta;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

