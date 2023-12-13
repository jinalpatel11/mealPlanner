package com.example.mealplanner.view;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.controller.UserController;
import com.example.mealplanner.databinding.ActivityHomeBinding;
import com.example.mealplanner.repositories.UserRepository;
import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.home.HomeActivity;
import com.example.mealplanner.view.home.SettingActivity;
import com.example.mealplanner.view.setting.DeveloperDetailsActivity;
import com.example.mealplanner.view.setting.HelpSettingActivity;
import com.example.mealplanner.view.setting.MyProfileActivity;
import com.example.mealplanner.view.setting.PrivacyPolicyActivity;
import com.example.mealplanner.view.setting.TermsServiceActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class BaseActivity extends AppCompatActivity {


    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_EMAIL_KEY = "user_email";

    protected UserController userController; // Accessible in subclasses


    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize UserController with UserRepository
        UserRepository userRepository = new UserRepository(getApplicationContext());
        userController = new UserController(userRepository);

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

    protected void navigateToMyProfile() {

        // Redirect to the MyProfile activity
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
        finish();
    }

    protected void navigateToDeveloperDetails() {
        // Redirect to the Developer details activity
        Intent intent = new Intent(this, DeveloperDetailsActivity.class);
        startActivity(intent);
        finish();
    }

    protected void redirectToHome() {
        // Redirect to HomeActivity
        startActivity(new Intent(this, HomeActivity.class));
        finish(); // Optional: finish the current activity
    }

    protected void navigateToPrivacyPolicy() {
        // Redirect to the Developer details activity
        Intent intent = new Intent(this, PrivacyPolicyActivity.class);
        startActivity(intent);
        finish();
    }
    protected void navigateToHelpSetting() {
        // Redirect to the Developer details activity
        Intent intent = new Intent(this, HelpSettingActivity.class);
        startActivity(intent);
        finish();
    }
    protected void navigateToTermsServic() {
        // Redirect to the Developer details activity
        Intent intent = new Intent(this, TermsServiceActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Check if the current activity is the HomeActivity
        if (this instanceof HomeActivity) {
            // Handle back navigation for HomeActivity
            // For example, show a confirmation dialog or take a specific action
            // You can customize this part based on your app's requirements
            showExitConfirmationDialog();
        } else {
            // For other activities, proceed with the default back navigation behavior
            super.onBackPressed();
        }
    }

    private void showExitConfirmationDialog() {
        // Implement your logic to show a confirmation dialog
        // You can use AlertDialog or a custom dialog for this purpose
        // For example:
        new AlertDialog.Builder(this)
                .setTitle("Exit Confirmation")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // Finish the current activity
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }



}

