package com.example.mealplanner.view.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.controller.UserController;
import com.example.mealplanner.databinding.ActivityRegistrationBinding;
import com.example.mealplanner.repositories.UserRepository;
import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.auth.RegistrationActivity;
import com.example.mealplanner.view.home.HomeActivity;
import com.example.mealplanner.view.home.SettingActivity;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        setupbackButton();
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

    private void redirectToSetting() {
        Intent settingIntent = new Intent(MyProfileActivity.this, SettingActivity.class);
        startActivity(settingIntent);
        finish();
    }

}


