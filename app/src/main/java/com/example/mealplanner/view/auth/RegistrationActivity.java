package com.example.mealplanner.view.auth;

// RegistrationActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mealplanner.controller.UserController;
import com.example.mealplanner.databinding.ActivityRegistrationBinding;
import com.example.mealplanner.repositories.UserRepository;

public class RegistrationActivity extends AppCompatActivity {
    private UserController userController;
    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UserController with UserRepository
        UserRepository userRepository = new UserRepository(getApplicationContext());

        userController = new UserController(userRepository);

        // Set up UI components and event handlers
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.editTextUsername.getText().toString();
                String password = binding.editTextPassword.getText().toString();

                if (userController.register(username, password)) {
                    // Successful registration
                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    // Redirect to LoginActivity
                    redirectToLogin();
                } else {
                    // Failed registration (username already exists)
                    Toast.makeText(RegistrationActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void redirectToLogin() {
        Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish(); // Finish the RegistrationActivity to prevent going back
    }
}

