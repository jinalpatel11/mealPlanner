package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityHomeBinding;
import com.example.mealplanner.databinding.ActivitySettingBinding;
import com.example.mealplanner.model.User;
import com.example.mealplanner.view.BaseActivity;
import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.setting.MyProfileActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class SettingActivity extends BaseActivity {

    private GoogleSignInClient googleSignInClient;

    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setupCancelButton();
        setupLogoutButton();
        setupinitialsButton();

        setUpHeaderInformation();
        //My Settings
        setupMyProfileButton();
        setupDeveloperButton();
        setupHelpSettingButton();
        setupPrivacyPolicyButton();
        setupTermsServiceButton();
    }



    private void setUpHeaderInformation() {
        String email = getUserEmailFromSession();
        Log.d("SettingActivity", "Email: " + email);

        String initials = extractInitial(email);
        Log.d("SettingActivity", "Initials: " + initials);
        binding.initialsTextView.setText(initials);
        binding.usernameTextView.setText(email);
        User user = userController.getUserByEmail(email);
        Log.d("SettingActivity", "User: " + user);

        if (user != null) {
            // Set Birthdate
            Date birthDate = user.getBirthdateAsDate();
            if (birthDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String formattedDate = dateFormat.format(birthDate);
                binding.birthYearTextView.setText(formattedDate);
            } else {
                binding.birthYearTextView.setText("N/A");
            }

            // Set Sex
            String sex = user.getSex();
            binding.sexTextView.setText(sex != null ? sex : "N/A");

            // Set Height
            String height = user.getHeight();
            binding.heightTextView.setText(height != null ? height : "N/A");
        } else {
            // Handle the case where the user does not exist
            Log.e("SettingActivity", "User is null");
            binding.birthYearTextView.setText("N/A");
            binding.sexTextView.setText("N/A");
            binding.heightTextView.setText("N/A");
        }

        // Rest of the code
    }



    private void setupCancelButton() {

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToHome();
            }
        });
    }

    private void setupLogoutButton() {

       binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              logout();
            }
        });
    }


    private void setupinitialsButton() {

      binding.initialsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MyProfileActivity
                startActivity(new Intent(SettingActivity.this, MyProfileActivity.class));
            }
        });
    }

    private void setupMyProfileButton() {

       binding.myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MyProfileActivity
                navigateToMyProfile();
              }
        });
    }

    private void setupDeveloperButton() {

        binding.developerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MyProfileActivity
                navigateToDeveloperDetails();
            }
        });
    }



    private void setupHelpSettingButton() {

        binding.helpSupportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MyProfileActivity
                navigateToHelpSetting();
            }
        });
    }


    private void setupPrivacyPolicyButton() {

        binding.privacyPolicyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MyProfileActivity
                navigateToPrivacyPolicy();
            }
        });
    }


    private void setupTermsServiceButton() {

        binding.termsServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MyProfileActivity
                navigateToTermsServic();
            }
        });
    }


}
