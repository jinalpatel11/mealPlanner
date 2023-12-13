package com.example.mealplanner.view.setting;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.controller.UserController;
import com.example.mealplanner.databinding.ActivityMyProfileBinding;
import com.example.mealplanner.databinding.ActivityRegistrationBinding;
import com.example.mealplanner.databinding.ActivitySettingBinding;
import com.example.mealplanner.model.User;
import com.example.mealplanner.repositories.UserRepository;
import com.example.mealplanner.view.BaseActivity;
import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.auth.RegistrationActivity;
import com.example.mealplanner.view.home.HomeActivity;
import com.example.mealplanner.view.home.SettingActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyProfileActivity extends BaseActivity {

    private ActivityMyProfileBinding binding;

    private  User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setupbackButton();



        String email = getUserEmailFromSession();
        Log.d("MyProfileActivity", "Email: " + email);

        User user = userController.getUserByEmail(email);
        Log.d("MyProfileActivity", "User: " + user);
        setupProfileData(user);

        // Set OnClickListener for each field TextView
        setFieldClickListener(R.id.firstNameTextView);
        setFieldClickListener(R.id.lastNameTextView);
        setFieldClickListener(R.id.heightTextView);
        setFieldClickListener(R.id.weightTextView);
    }

    private void setupbackButton() {
        ImageButton cancelButton = findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToSetting();
            }
        });
    }


    private void setupProfileData(User user) {

        if (user != null) {
            this.user = user;
            binding.firstNameTextView.setText(user.getFirstName());
            binding.lastNameTextView.setText(user.getLastName());
            binding.sexTextView.setText(user.getSex());
            binding.birthdayTextView.setText(user.getBirthdate());
            binding.heightTextView.setText(user.getHeight());
            binding.weightTextView.setText(user.getWeight());
            binding.activityLevelTextView.setText(user.getActivityLevel());
            // Add other fields as needed
        } else {
            // Handle the case where the user does not exist
            Log.e("MyProfileActivity", "User is null");
        }

    }



    private void redirectToSetting() {
        Intent settingIntent = new Intent(MyProfileActivity.this, SettingActivity.class);
        startActivity(settingIntent);
        finish();
    }


    private void setFieldClickListener(int fieldTextViewId) {
        TextView fieldTextView = findViewById(fieldTextViewId);
        fieldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateFieldDialog(fieldTextViewId);
            }
        });
    }

    private void showUpdateFieldDialog(int fieldTextViewId) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_field);

        // Add your logic to handle updating the field in the dialog

        TextView fieldValueTextView = dialog.findViewById(R.id.fieldEditText);
        fieldValueTextView.setText(getFieldValue(fieldTextViewId)); // Get the current field value

        Button updateButton = dialog.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the update logic here
                // Close the dialog after updating
                EditText editText = dialog.findViewById(R.id.fieldEditText);
                String value = editText.getText().toString();
                String email = getUserEmailFromSession();
                User user = userController.getUserByEmail(email);

                if (fieldTextViewId == R.id.firstNameTextView) {
                    user.setFirstName(value);
                } else if (fieldTextViewId == R.id.lastNameTextView) {
                    user.setLastName(value);
                } else if (fieldTextViewId == R.id.heightTextView) {
                    user.setHeight(value);
                } else if (fieldTextViewId == R.id.weightTextView) {
                    user.setWeight(value);
                }

                // Update the user
                boolean isUpdateSuccessful = userController.updateUserModel(user);

                if (isUpdateSuccessful) {
                    // Update successful
                    // Update the UI with the new user information
                    setupProfileData(user);

                } else {
                    // Update failed (user with the specified email might not exist)
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private String getFieldValue(int fieldTextViewId) {
        if (fieldTextViewId == R.id.firstNameTextView) {
            return user.getFirstName();
        } else if (fieldTextViewId == R.id.lastNameTextView) {
            return user.getLastName();
        } else if (fieldTextViewId == R.id.heightTextView) {
            return user.getHeight();
        } else if (fieldTextViewId == R.id.weightTextView) {
            return user.getWeight();
        }
        // Add similar conditions for other fields
        else {
            // Handle unknown field ID (should not happen in a well-defined system)
            return "Unknown Field";
        }
    }

}


