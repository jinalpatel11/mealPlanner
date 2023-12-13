package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        TextView activityLevelDetailTextView = findViewById(R.id.activityLevelDetailTextView);
        ImageView activityLevelImageView = findViewById(R.id.activityLevelImageView);
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
                        activityLevelDetailTextView.setText("Desk job with little or no exercise.");
                        activityLevelImageView.setImageResource(R.drawable.sedentary);
                        break;
                    case 1:
                        activityLevelValueTextView.setText("Lightly Active");
                        activityLevelDetailTextView.setText("Work a job with light physical demands, or work a desk job and perform light exercise (at the level of a brisk walk) for 30 minutes per day, 3-5 times per week.");
                        activityLevelImageView.setImageResource(R.drawable.lightly_active);
                        break;
                    case 2:
                        activityLevelValueTextView.setText("Moderately Active");
                        activityLevelDetailTextView.setText("Work a moderately physically demanding job, such as constructon worker, or work a desk job and engage in moderate exercise for 1 hour per day, 3-5 times per week.");
                        activityLevelImageView.setImageResource(R.drawable.moderatelyactive);

                        break;
                    case 3:
                        activityLevelValueTextView.setText("Very Active");
                        activityLevelDetailTextView.setText("Work a consistently physically demanding job, such as agricultural worker, or work a desk job and engage in intense exercise for 1 hour per day, or moderate exercise for 2 hours per day, 5-7 times per week.");
                        activityLevelImageView.setImageResource(R.drawable.veryactive);

                        break;
                    case 4:
                        activityLevelValueTextView.setText("Extra Active");
                        activityLevelDetailTextView.setText("Work an extremely physically demanding job, such as professional athlete, competitive cyclist, or fitness professional, or engage in intense exercise for at least 2 hours per day.");
                        activityLevelImageView.setImageResource(R.drawable.extraactive);

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
