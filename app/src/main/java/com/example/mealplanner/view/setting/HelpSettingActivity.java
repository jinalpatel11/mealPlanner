package com.example.mealplanner.view.setting;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityDeveloperDetailsBinding;
import com.example.mealplanner.databinding.ActivityHelpSettingBinding;
import com.example.mealplanner.model.Developer;
import com.example.mealplanner.model.InfoPageModel;
import com.example.mealplanner.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HelpSettingActivity  extends BaseActivity {

    private ActivityHelpSettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sample data for pages
        List<InfoPageModel> infoPagesList = new ArrayList<>();
        infoPagesList.add(new InfoPageModel("Contact Us:", "If you have any questions, concerns, or feedback regarding the Meal Planner application, our support team is here to assist you. You can reach us via email at support@mealplannerapp.com."));
        infoPagesList.add(new InfoPageModel("FAQs:", "Check our Frequently Asked Questions (FAQs) section for quick answers to common queries. Visit the Help Center on our website for more information."));
        infoPagesList.add(new InfoPageModel("User Guide:", "Explore the comprehensive User Guide within the app for step-by-step instructions on using the features of the Meal Planner application."));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewInfoPages);
        InfoPagesAdapter infoPagesAdapter = new InfoPagesAdapter(infoPagesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(infoPagesAdapter);
    }
}
