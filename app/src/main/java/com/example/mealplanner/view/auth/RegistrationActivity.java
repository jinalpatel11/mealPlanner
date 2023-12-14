package com.example.mealplanner.view.auth;

// RegistrationActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.controller.UserController;
import com.example.mealplanner.databinding.ActivityRegistrationBinding;
import com.example.mealplanner.model.User;
import com.example.mealplanner.repositories.UserRepository;
import com.example.mealplanner.view.BaseActivity;
import com.example.mealplanner.view.home.ActivityBirthQuestion;
import com.example.mealplanner.view.home.ActivityHeightQuestion;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class RegistrationActivity extends BaseActivity {

    private ActivityRegistrationBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up UI components and event handlers
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateFields())
                {
                    String firstName = binding.editTextFirstName.getText().toString();
                    String password = binding.editTextPassword.getText().toString();
                    String email = binding.editTextEmail.getText().toString();

                    Intent intent = getIntent();
                    if (intent != null) {
                        User user = intent.getParcelableExtra("user");

                        if (user != null) {

                            user.setFirstName(firstName);
                            user.setPassword(password);
                            user.setEmail(email);

                            if (userController.register(user)) {
                                // Successful registration
                                Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                // Redirect to LoginActivity
                                redirectToLogin();
                            } else {
                                // Failed registration (email already exists)
                                Toast.makeText(RegistrationActivity.this, "Email already exists try to login", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                }else
                {

                }

            }
        });



        setupGoogleSignInLogin();

        binding.googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });
    }



    private void redirectToLogin() {
        Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish(); // Finish the RegistrationActivity to prevent going back
    }




    // Validate user input fields
    private boolean validateFields() {
        boolean validationValue = true;
        String name = Objects.requireNonNull(binding.editTextFirstName.getText()).toString().trim();
        String email = Objects.requireNonNull(binding.editTextEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.editTextPassword.getText()).toString().trim();

        if (name.isEmpty()) {
            binding.editTextFirstName.setError(getString(R.string.name_required));
            validationValue = false;
        } else {
            binding.editTextFirstName.setError(null);
        }

        if (email.isEmpty()) {
            binding.editTextEmail.setError(getString(R.string.email_required));
            validationValue = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.setError(getString(R.string.invalid_email));
            validationValue = false;
        } else {
            binding.editTextEmail.setError(null);
        }

        // Validate password
        if (password.isEmpty()) {
            binding.editTextPassword.setError(getString(R.string.password_required));
            validationValue = false;
        } else if (!isPasswordValid(password)) {
            binding.editTextPassword.setError(getString(R.string.invalid_password));
            validationValue = false;
        } else {
            binding.editTextPassword.setError(null);
        }

        return validationValue;
    }

    private boolean isPasswordValid(String password) {
        // Use a regular expression to check if the password meets the criteria
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(passwordPattern);
    }

}

