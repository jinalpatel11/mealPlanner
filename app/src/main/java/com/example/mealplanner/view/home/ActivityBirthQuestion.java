package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.model.User;

import java.util.Calendar;

public class ActivityBirthQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_question);

        Button continueButton = findViewById(R.id.btnContinue);
        DatePicker datePicker = findViewById(R.id.datePicker);

        // Set max date to the current date
        Calendar calendar = Calendar.getInstance();
        datePicker.setMaxDate(calendar.getTimeInMillis());

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the selected date from DatePicker
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int dayOfMonth = datePicker.getDayOfMonth();

                // Do something with the selected date...
                Intent intent = getIntent();
                if (intent != null) {
                    User user = intent.getParcelableExtra("user");

                    if (user != null) {
                        // Access the weight and weight unit from the User object
                        String weight = user.getWeight();
                        String weightUnit = user.getWeightUnit();

                        // Use the weight information as needed
                        Log.d("UserWeight", "Weight: " + weight + " " + weightUnit);
                        String birthday = year + "-" + month + "-" + dayOfMonth;
                        user.setBirthdate(birthday);

                        Log.d("UserValues", "Username: " + user.getUsername());
                        Log.d("UserValues", "Weight: " + user.getWeight() + " " + user.getWeightUnit());
                        Log.d("UserValues", "Target Weight: " + user.getTargetWeight() + " " + user.getTargetWeightUnit());

                        // Pass the updated User object to the next activity
                        intent = new Intent(ActivityBirthQuestion.this, ActivityLifeStyleQuestion.class);
                        intent.putExtra("user",(Parcelable) user);
                        startActivity(intent);

                    }

                }

            }



        });
    }
}
