package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.model.GoalItem;

import java.util.List;

public class ActivityWeightQuestion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_question);

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
}
