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
import com.example.mealplanner.model.GoalItem;
import com.example.mealplanner.model.User;
import com.example.mealplanner.view.BaseActivity;

import java.io.Serializable;
import java.util.List;

public class ActivityWeightQuestion extends BaseActivity {

    private EditText edtWeightValue;
    private Button btnKg, btnLb;

    private String selectedUnit = "KG"; // Initial selected unit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_question);

        edtWeightValue = findViewById(R.id.edtWeightValue);
        btnKg = findViewById(R.id.btnKg);
        btnLb = findViewById(R.id.btnLb);

        // Set initial weight in kilograms
        double initialWeightKg = 70.0; // Replace this with your initial weight
        edtWeightValue.setText(String.valueOf(initialWeightKg));

        // Change the color of the "KG" button to indicate it's selected initially
        btnKg.setBackgroundColor(getResources().getColor(R.color.primary));
        btnLb.setBackgroundColor(getResources().getColor(R.color.second));

        // Set OnClickListener for KG and LB buttons
        btnKg.setOnClickListener(v -> {
            selectedUnit = "KG";
            btnKg.setBackgroundColor(getResources().getColor(R.color.primary));
            btnLb.setBackgroundColor(getResources().getColor(R.color.second));
        });

        btnLb.setOnClickListener(v -> {
            selectedUnit = "LB";
            btnLb.setBackgroundColor(getResources().getColor(R.color.primary));
            btnKg.setBackgroundColor(getResources().getColor(R.color.second));
        });



        Button btnContinue = findViewById(R.id.btnContinue);


        Intent intent = getIntent();
        if(intent != null) {
            List<GoalItem> selectedGoals = intent.getParcelableArrayListExtra("selectedGoals");

            if(selectedGoals != null && !selectedGoals.isEmpty()) {
                for(GoalItem goalItem : selectedGoals) {
                    // Log the received goal item properties
                    Log.d("SelectedGoal", "Image Resource: " + goalItem.getImageResource() + ", Text: " + goalItem.getText());
                }
            } else {
                Log.d("SelectedGoal", "No selected goals received");
            }
        } else {
            Log.d("SelectedGoal", "Intent is null");
        }



        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the weight value from the EditText
                String weightValue = edtWeightValue.getText().toString();


                // Validate the input
                if (weightValue.isEmpty() || !isValidWeight(weightValue)) {
                    // Show an error message
                    edtWeightValue.setError("Please enter a number between 1 and 1000");
                    return;
                }

                // Clear any previous error messages
                edtWeightValue.setError(null);



                // Create a User object or retrieve the existing one from your application logic
                User user = new User();

                // Set the weight value and unit in the User object
                user.setWeight(weightValue);
                user.setWeightUnit(selectedUnit);

                // Pass the User object to the next activity
                Intent intent = new Intent(ActivityWeightQuestion.this, ActivityWeightGoalQuestion.class);
                intent.putExtra("user", (Parcelable) user);
                intent.putExtra("stepCount", 2); // Initial step count
                startActivity(intent);
            }
        });

    }

}
