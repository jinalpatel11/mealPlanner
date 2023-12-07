package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.model.User;

public class ActivityWeightGoalQuestion extends AppCompatActivity {
    private EditText edtTargetWeightValue;
    private Button btnTargetKg, btnTargetLb;

    private String selectedTargetUnit = "KG"; // Initial selected target unit

    private  User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_goal_question);

        edtTargetWeightValue = findViewById(R.id.edtTargetWeightValue);
        btnTargetKg = findViewById(R.id.btnKg);
        btnTargetLb = findViewById(R.id.btnLb);

        // Set OnClickListener for Target KG and LB buttons
        btnTargetKg.setOnClickListener(v -> {
            selectedTargetUnit = "KG";
            btnTargetKg.setBackgroundColor(getResources().getColor(R.color.primary));
            btnTargetLb.setBackgroundColor(getResources().getColor(R.color.second));
        });

        btnTargetLb.setOnClickListener(v -> {
            selectedTargetUnit = "LB";
            btnTargetLb.setBackgroundColor(getResources().getColor(R.color.primary));
            btnTargetKg.setBackgroundColor(getResources().getColor(R.color.second));
        });

        Button btnContinue = findViewById(R.id.btnContinue);


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the target weight value from the EditText
                String targetWeightValue = edtTargetWeightValue.getText().toString();


                Intent intent = getIntent();
                if (intent != null) {
                    user = intent.getParcelableExtra("user");

                    if (user != null) {
                        // Access the weight and weight unit from the User object
                        String weight = user.getWeight();
                        String weightUnit = user.getWeightUnit();

                        // Use the weight information as needed
                        Log.d("UserWeight", "Weight: " + weight + " " + weightUnit);

                        user.setTargetWeight(targetWeightValue);
                        user.setTargetWeightUnit(selectedTargetUnit);

                        Log.d("UserValues", "Username: " + user.getUsername());
                        Log.d("UserValues", "Weight: " + user.getWeight() + " " + user.getWeightUnit());
                        Log.d("UserValues", "Target Weight: " + user.getTargetWeight() + " " + user.getTargetWeightUnit());

                    }

                }


                // Pass the updated User object to the next activity
                intent = new Intent(ActivityWeightGoalQuestion.this, ActivitySexQuestion.class);
                intent.putExtra("user",(Parcelable) user);
                startActivity(intent);
            }
        });
    }
}
