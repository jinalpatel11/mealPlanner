package com.example.mealplanner.view.auth;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.controller.UserController;
import com.example.mealplanner.databinding.ActivityLoginBinding;
import com.example.mealplanner.repositories.UserRepository;
import com.example.mealplanner.view.BaseActivity;
import com.example.mealplanner.view.home.AppHomeActivity;
import com.example.mealplanner.view.home.HomeActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    private UserController userController;
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UserController with UserRepository
        UserRepository userRepository = new UserRepository(getApplicationContext());

        userController = new UserController(userRepository);

        // Set up Google Sign-In
        setupGoogleSignInLogin();

        // Set up UI components and event handlers
        setupUI();


    }


    private void setupUI() {
        // Set up click listeners for buttons
        binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNormalSignIn();
            }
        });

        binding.googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });

        // Handle registration button click
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToRegistration();
            }
        });
    }

    private void handleNormalSignIn() {
        String email = binding.editTextEmail.getText().toString();
        String password = binding.editTextPassword.getText().toString();

        if (userController.signIn(email, password)) {
            // Sign-in successful
            showToast("Sign-in successful");
            saveUserEmailInSession(email);
            // Redirect to HomeActivity
            redirectToHome();
        } else {
            // Sign-in failed
            showToast("Sign-in failed");
        }
    }

    private void redirectToRegistration() {
        Intent registerIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(registerIntent);
    }







}
