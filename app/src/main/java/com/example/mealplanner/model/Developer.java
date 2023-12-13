package com.example.mealplanner.model;

public class Developer {
    private String name;
    private String details;
    private int imageResourceId;

    public Developer(String name, String details, int imageResourceId) {
        this.name = name;
        this.details = details;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

