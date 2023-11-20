package com.example.mealplanner.model;

// Ingredient.java

import java.io.Serializable;
import java.util.List;

public class Ingredient implements Serializable {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

