package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.model.User;

public class ActivitySexQuestion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_question);

        Button btnContinue = findViewById(R.id.btnContinue);
        Button continueButton = findViewById(R.id.btnContinue);
        RadioGroup sexRadioGroup = findViewById(R.id.sexRadioGroup);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected radio button's ID from the RadioGroup
                int selectedRadioButtonId = sexRadioGroup.getCheckedRadioButtonId();
                Log.d("RadioButtonValue", String.valueOf(selectedRadioButtonId));
                // Check if a radio button is selected
                if (selectedRadioButtonId != -1) {

                    Intent intent = getIntent();
                    if (intent != null) {
                      User user = intent.getParcelableExtra("user");

                        if (user != null) {
                            // Access the weight and weight unit from the User object
                            String weight = user.getWeight();
                            String weightUnit = user.getWeightUnit();

                            // Use the weight information as needed
                            Log.d("UserWeight", "Weight: " + weight + " " + weightUnit);


                            Log.d("UserValues", "Username: " + user.getUsername());
                            Log.d("UserValues", "Weight: " + user.getWeight() + " " + user.getWeightUnit());
                            Log.d("UserValues", "Target Weight: " + user.getTargetWeight() + " " + user.getTargetWeightUnit());

                            // Find the selected radio button
                            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                            // Get the text (or other data) from the selected radio button
                            String selectedSex = selectedRadioButton.getText().toString();
                            user.setSex(selectedSex);

                            // Now you can use the selectedSex value as needed
                            // For example, you can pass it to the next activity using Intent
                            intent = new Intent(ActivitySexQuestion.this, ActivityHeightQuestion.class);
                            intent.putExtra("user",(Parcelable) user);
                            startActivity(intent);
                        }

                    }



                } else {
                    // No radio button is selected, handle this case if needed
                    Toast.makeText(ActivitySexQuestion.this, "Please select a sex", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
