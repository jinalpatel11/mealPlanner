package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.model.GoalItem;

import java.util.List;

public class ActivityWeightQuestion extends AppCompatActivity {

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
                // Pass selected choice to ActivityWeightQuestion
                Intent intent = new Intent(ActivityWeightQuestion.this, ActivityWeightGoalQuestion.class);
                // Add extras to pass data to next activity
                // Example: intent.putExtra("selectedActivityType", selectedActivityType);
                intent.putExtra("stepCount", 2); // Initial step count
                startActivity(intent);
            }
        });
    }


    public void convertToKg(View view) {
        // Handle conversion to KG when KG button is clicked
        String weightString = edtWeightValue.getText().toString();
        if (!weightString.isEmpty()) {
            double weight = Double.parseDouble(weightString);
            // Convert to KG (1 LB = 0.453592 KG)
            double kgWeight = weight * 0.453592;
            edtWeightValue.setText(String.valueOf(kgWeight));
        }
    }

    public void convertToLb(View view) {
        // Handle conversion to LB when LB button is clicked
        String weightString = edtWeightValue.getText().toString();
        if (!weightString.isEmpty()) {
            double weight = Double.parseDouble(weightString);
            // Convert to LB (1 KG = 2.20462 LB)
            double lbWeight = weight * 2.20462;
            edtWeightValue.setText(String.valueOf(lbWeight));
        }
    }
}
