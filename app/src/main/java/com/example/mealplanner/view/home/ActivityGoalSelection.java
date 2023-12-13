package com.example.mealplanner.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.GoalItem;
import com.example.mealplanner.model.RecipeItem;
import com.example.mealplanner.view.meal.RecipesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityGoalSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_selection);

        // goal RecyclerView
        RecyclerView goalsRecyclerView = findViewById(R.id.goalsRecyclerView);

        // Set up the layout manager to display two items per row
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        goalsRecyclerView.setLayoutManager(layoutManager);

        // In your activity or fragment
        List<GoalItem> goalItems = new ArrayList<>();
        goalItems.add(new GoalItem(R.drawable.icons8fire50, "Lose Weight"));
        goalItems.add(new GoalItem(R.drawable.icons8muscle50, "Build muscle"));
        goalItems.add(new GoalItem(R.drawable.icons8energy50, "Increase energy"));
        goalItems.add(new GoalItem(R.drawable.icons8moon50, "Improve sleep"));
        goalItems.add(new GoalItem(R.drawable.icons8drop50, "Manage glucose"));
        goalItems.add(new GoalItem(R.drawable.icons8pulse50, "Be healthier"));

        // Set up the adapter for the RecyclerView
        GoalAdapter recipesAdapter = new GoalAdapter(goalItems);
        goalsRecyclerView.setAdapter(recipesAdapter);


        Button btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the list of selected goals from the adapter
                List<GoalItem> selectedGoals = recipesAdapter.getSelectedGoals();


                // Check the size of the selected goals list
                Log.d("SelectedGoals", "Number of selected goals: " + selectedGoals.size());

                // Log the selected goals if the list is not empty
                if (!selectedGoals.isEmpty()) {
                    for (GoalItem goal : selectedGoals) {
                        int imageResource = goal.getImageResource();
                        String text = goal.getText();
                        Log.d("SelectedGoals", "Image Resource: " + imageResource + ", Text: " + text);
                    }
                } else {
                    Log.d("SelectedGoals", "No goals selected");
                }

                // Create intent for the next activity
                Intent intent = new Intent(ActivityGoalSelection.this, ActivityWeightQuestion.class);

                // Pass the list of selected goals as an extra to the next activity
                intent.putParcelableArrayListExtra("selectedGoals", new ArrayList<Parcelable>(selectedGoals));
                 // Add other extras if needed
                intent.putExtra("stepCount", 2); // Initial step count

                // Start the next activity
                startActivity(intent);
            }

        });
    }




}
