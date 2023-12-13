package com.example.mealplanner.view.setting;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityDeveloperDetailsBinding;
import com.example.mealplanner.databinding.ActivityHomeBinding;
import com.example.mealplanner.databinding.ActivitySettingBinding;
import com.example.mealplanner.model.Developer;
import com.example.mealplanner.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DeveloperDetailsActivity extends BaseActivity {

    private ActivityDeveloperDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeveloperDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sample developer data
        List<Developer> developerList = new ArrayList<>();
        developerList.add(new Developer("Jinalben Patel", "Details for Developer 1", R.drawable.jinal_patel));
        developerList.add(new Developer("Yash Patel", "Details for Developer 2", R.drawable.jinal_patel));
        developerList.add(new Developer("Falgun Gandhi", "Details for Developer 3", R.drawable.jinal_patel));
        developerList.add(new Developer("Utkarsh Aman", "Details for Developer 4", R.drawable.jinal_patel));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDevelopers);
        DeveloperAdapter developerAdapter = new DeveloperAdapter(developerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(developerAdapter);
    }

}
