package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.setting.MyProfileActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class SettingActivity extends AppCompatActivity {

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setupCancelButton();
        setupLogoutButton();
        setupinitialsButton();
        //My Settings
        setupMyProfileButton();
    }

    private void redirectToHome() {
        // Redirect to HomeActivity
        startActivity(new Intent(SettingActivity.this, HomeActivity.class));
        finish(); // Optional: finish the current activity
    }

    private void setupCancelButton() {
        ImageButton cancelButton = findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToHome();
            }
        });
    }

    private void setupLogoutButton() {
        ImageButton logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
                navigateToLogin();
            }
        });
    }

    private void navigateToLogin() {
        Intent loginIntent = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void signOut() {
        googleSignInClient.signOut().addOnCompleteListener(this,
                task -> {
                    // Optional: Perform any additional sign-out actions
                });
    }


    private void setupinitialsButton() {
        TextView initialsButton = findViewById(R.id.initialsTextView);

        initialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MyProfileActivity
                startActivity(new Intent(SettingActivity.this, MyProfileActivity.class));
            }
        });
    }

    private void setupMyProfileButton() {
        LinearLayout myProfileButton = findViewById(R.id.myProfileButton);

        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MyProfileActivity
                startActivity(new Intent(SettingActivity.this, MyProfileActivity.class));
            }
        });
    }
}
