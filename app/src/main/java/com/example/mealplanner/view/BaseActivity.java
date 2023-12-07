package com.example.mealplanner.view;


import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.home.HomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class BaseActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_EMAIL_KEY = "user_email";

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected String getUserEmailFromSession() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_EMAIL_KEY, null);
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void logout() {
        // Clear the stored user email
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(USER_EMAIL_KEY);
        editor.apply();
        googleSignOut();
        navigateToLogin();


    }

    protected void setupGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    // Function to extract the initial from the email address
    protected String extractInitial(String email) {
        // Check if the email is not null and contains the "@" symbol
        if (email != null && email.contains("@")) {
            // Extract the part before the "@" symbol
            String[] parts = email.split("@");
            if (parts.length > 0) {
                // Get the first part (username) and extract the initial
                String username = parts[0];
                if (!username.isEmpty()) {
                    // Return the first character as the initial
                    return String.valueOf(username.charAt(0));
                }
            }
        }
        // Return a default value or handle the case where the email format is unexpected
        return "";
    }


    private void googleSignOut() {
        // Sign out from Google Sign-In
        googleSignInClient.signOut().addOnCompleteListener(this,
                task -> {
                    // Optional: Perform any additional sign-out actions
                });
    }

    private void navigateToLogin() {
        // Navigate to the LoginActivity and finish the current activity

        // Redirect to the login activity
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}

