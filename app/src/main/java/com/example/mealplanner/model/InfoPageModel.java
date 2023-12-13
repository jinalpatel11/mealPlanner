package com.example.mealplanner.model;

public class InfoPageModel {
    private String title;
    private String content;

    public InfoPageModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

