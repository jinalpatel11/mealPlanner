package com.example.mealplanner.view.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityHelpSettingBinding;
import com.example.mealplanner.model.InfoPageModel;
import com.example.mealplanner.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TermsServiceActivity extends BaseActivity {

    private ActivityHelpSettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sample data for pages
        List<InfoPageModel> infoPagesList = new ArrayList<>();
        infoPagesList.add(new InfoPageModel("Introduction:", "By using the Meal Planner application, you agree to comply with and be bound by the following Terms of Service. If you do not agree to these terms, please refrain from using the application"));
        infoPagesList.add(new InfoPageModel("User Conduct:","Users are responsible for their actions while using the Meal Planner app. Any misuse, unauthorized access, or violation of the terms may result in the termination of your account."));
        infoPagesList.add(new InfoPageModel("Intellectual Property:","All content and materials provided by the Meal Planner app, including recipes and meal plans, are protected by copyright. Users may not reproduce, distribute, or create derivative works without explicit permission."));
        infoPagesList.add(new InfoPageModel("Disclaimer:","The Meal Planner app is not responsible for any adverse effects resulting from the use of suggested recipes or meal plans. Consult with a healthcare professional for personalized dietary advice."));
        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewInfoPages);
        InfoPagesAdapter infoPagesAdapter = new InfoPagesAdapter(infoPagesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(infoPagesAdapter);

        //set up cancel button
        setupCancelButton();
    }

    private  void setupCancelButton(){
        // Assuming you have a button in your layout with the ID "backButton"
        ImageButton backButton = findViewById(R.id.cancelButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call onBackPressed to simulate the back button press
                onBackPressed();
            }
        });
    }

}
