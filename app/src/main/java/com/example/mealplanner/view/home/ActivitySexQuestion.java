package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;

public class ActivitySexQuestion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_question);

        Button btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass selected choice to ActivityWeightQuestion
                Intent intent = new Intent(ActivitySexQuestion.this, ActivityHeightQuestion.class);
                // Add extras to pass data to next activity
                // Example: intent.putExtra("selectedActivityType", selectedActivityType);
                intent.putExtra("stepCount", 4); // Initial step count
                startActivity(intent);
            }
        });
    }
}
