package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.model.User;
import com.example.mealplanner.view.auth.RegistrationActivity;

public class ActivityLifeStyleQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_style_question);

        Button btnContinue = findViewById(R.id.btnContinue);
        SeekBar slider = findViewById(R.id.slider);
        SeekBar activityLevelSlider = findViewById(R.id.activityLevelSlider);
        TextView sliderValueTextView = findViewById(R.id.sliderValueTextView);
        TextView activityLevelValueTextView = findViewById(R.id.activityLevelValueTextView);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 // Add extras to pass data to the next activity
                int selectedActivityLevelValue = activityLevelSlider.getProgress(); // Adjust range if needed

                Intent intent = getIntent();
                if (intent != null) {
                    User user = intent.getParcelableExtra("user");

                    if (user != null) {
                        // Access the weight and weight unit from the User object
                        String weight = user.getWeight();
                        String weightUnit = user.getWeightUnit();

                        // Use the weight information as needed
                        Log.d("UserWeight", "Weight: " + weight + " " + weightUnit);

                        user.setActivityLevel(String.valueOf(selectedActivityLevelValue));

                        Log.d("UserValues", "Username: " + user.getUsername());
                        Log.d("UserValues", "Weight: " + user.getWeight() + " " + user.getWeightUnit());
                        Log.d("UserValues", "Target Weight: " + user.getTargetWeight() + " " + user.getTargetWeightUnit());

                        // Pass the updated User object to the next activity
                        intent = new Intent(ActivityLifeStyleQuestion.this, RegistrationActivity.class);
                        intent.putExtra("user",(Parcelable) user);
                        startActivity(intent);

                    }

                }



            }
        });

        activityLevelSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update the TextView with the corresponding activity level based on the slider value
                switch (progress) {
                    case 0:
                        activityLevelValueTextView.setText("Sedentary");
                        break;
                    case 1:
                        activityLevelValueTextView.setText("Lightly Active");
                        break;
                    case 2:
                        activityLevelValueTextView.setText("Moderately Active");
                        break;
                    case 3:
                        activityLevelValueTextView.setText("Very Active");
                        break;
                    case 4:
                        activityLevelValueTextView.setText("Extra Active");
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed for this example
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed for this example
            }
        });
    }
}
