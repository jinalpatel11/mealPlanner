package com.example.mealplanner.view.home;

// HomePageActivity.java
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R; // Assuming you have a resource for the home page layout

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Use your home page layout resource
    }
}

