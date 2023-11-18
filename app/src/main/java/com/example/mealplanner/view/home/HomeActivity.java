package com.example.mealplanner.view.home;
// HomeActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.databinding.ActivityHomeBinding;
import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.meal.MealListActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve the email address from the Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USER_EMAIL")) {
            String userEmail = intent.getStringExtra("USER_EMAIL");

            // Display the email address using View Binding
            binding.emailTextView.setText("Welcome, " + userEmail);
        }

        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Set up click listener for Logout Button using View Binding
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout action
                signOut(); // Sign out from Google Sign-In
                navigateToLogin(); // Navigate to the LoginActivity and finish the current activity
            }
        });

        binding.mealListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navigateToMealList(); // Navigate to the MealListActivity
            }
        });


    }

    private void signOut() {
        // Sign out from Google Sign-In
        googleSignInClient.signOut().addOnCompleteListener(this,
                task -> {
                    // Optional: Perform any additional sign-out actions
                });
    }

    private void navigateToLogin() {
        // Navigate to the LoginActivity and finish the current activity
        Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
    private void navigateToMealList() {
        // Navigate to the MealListActivity and finish the current activity
        Intent loginIntent = new Intent(HomeActivity.this, MealListActivity.class);
        startActivity(loginIntent);

    }
}
