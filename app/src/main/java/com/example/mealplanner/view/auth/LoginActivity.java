package com.example.mealplanner.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.controller.UserController;
import com.example.mealplanner.databinding.ActivityLoginBinding;
import com.example.mealplanner.repositories.UserRepository;
import com.example.mealplanner.view.home.AppHomeActivity;
import com.example.mealplanner.view.home.HomeActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private UserController userController;
    private ActivityLoginBinding binding;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UserController with UserRepository
        UserRepository userRepository = new UserRepository(getApplicationContext());

        userController = new UserController(userRepository);

        // Set up Google Sign-In
        setupGoogleSignIn();

        // Set up UI components and event handlers
        setupUI();


    }

    private void setupGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
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

    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGoogleSignInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Google Sign-In connection failed", Toast.LENGTH_SHORT).show();
    }

    private void handleGoogleSignInResult(GoogleSignInResult result) {
        //Check if the email is already register on app or not

        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();


            String userEmail = account.getEmail();
            if(userController.checkUserEmailIdExist(userEmail))
            {
                // Now you can use 'account' to authenticate the user or extract user details
                // For example, you can get the user's display name: account.getDisplayName()
                // and email: account.getEmail()
                Toast.makeText(this, "Google Sign-In successful", Toast.LENGTH_SHORT).show();

                // Pass the email address to HomeActivity
                Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                homeIntent.putExtra("USER_EMAIL", userEmail);
                startActivity(homeIntent);

                finish(); // Optional: finish the LoginActivity to prevent going back
            }else
            {
                //redirect to app home activity to get register user on app
                // Pass the email address to HomeActivity
                Intent homeIntent = new Intent(LoginActivity.this, AppHomeActivity.class);
                homeIntent.putExtra("USER_EMAIL", userEmail);
                startActivity(homeIntent);

                finish(); // Optional: finish the LoginActivity to prevent going back
            }



        } else {
            Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void redirectToHome() {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        finish(); // Optional: finish the LoginActivity to prevent going back
    }
}
