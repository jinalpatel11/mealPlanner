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
import com.example.mealplanner.view.BaseActivity;

public class ActivityHeightQuestion extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_question);

        Button btnContinue = findViewById(R.id.btnContinue);
        EditText edtHeightValue = findViewById(R.id.edtHeightValue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the target weight value from the EditText
                String heightValue = edtHeightValue.getText().toString();

                // Validate the input
                if (heightValue.isEmpty() || !isValidHeight(heightValue)) {
                    // Show an error message
                    edtHeightValue.setError("Please enter a number between 90 and 240");
                    return;
                }

                // Clear any previous error messages
                edtHeightValue.setError(null);


                Intent intent = getIntent();
                if (intent != null) {
                  User user = intent.getParcelableExtra("user");

                    if (user != null) {
                        // Access the weight and weight unit from the User object
                        String weight = user.getWeight();
                        String weightUnit = user.getWeightUnit();

                        // Use the weight information as needed
                        Log.d("UserWeight", "Weight: " + weight + " " + weightUnit);

                        user.setHeight(heightValue);

                        Log.d("UserValues", "Username: " + user.getUsername());
                        Log.d("UserValues", "Weight: " + user.getWeight() + " " + user.getWeightUnit());
                        Log.d("UserValues", "Target Weight: " + user.getTargetWeight() + " " + user.getTargetWeightUnit());

                        // Pass the updated User object to the next activity
                        intent = new Intent(ActivityHeightQuestion.this, ActivityBirthQuestion.class);
                        intent.putExtra("user",(Parcelable) user);
                        startActivity(intent);

                    }

                }



            }
        });


    }
}
