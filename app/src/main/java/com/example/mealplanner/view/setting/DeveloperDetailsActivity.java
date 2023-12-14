package com.example.mealplanner.view.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
        developerList.add(new Developer("Jinalben Patel", "Details:Jinalben Patel is an experienced Android developer with a strong background in building robust and user-friendly mobile applications. She specializes in creating seamless and efficient user interfaces, optimizing app performance, and implementing the latest Android technologies.", R.drawable.jinal_patel));
        developerList.add(new Developer("Yash Patel", "Details: Yash Patel is a skilled Android developer known for his expertise in developing innovative and scalable mobile solutions. With a passion for clean code and optimal app architecture, Yash excels in creating apps that deliver a great user experience.", R.drawable.yash));
        developerList.add(new Developer("Utkarsh Aman", "Details: Falgun Gandhi is a dedicated Android developer who brings creativity and attention to detail to every project. His proficiency in designing user interfaces and implementing cutting-edge features contributes to the success of Android applications.", R.drawable.utkarsh));
        developerList.add(new Developer("Falgun Gandhi", "Details: Utkarsh Aman is a talented Android developer known for his problem-solving skills and commitment to delivering high-quality software. With a focus on optimizing app performance and ensuring a smooth user experience, Utkarsh is a valuable asset to any Android development team.", R.drawable.img));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDevelopers);
        DeveloperAdapter developerAdapter = new DeveloperAdapter(developerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(developerAdapter);

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
