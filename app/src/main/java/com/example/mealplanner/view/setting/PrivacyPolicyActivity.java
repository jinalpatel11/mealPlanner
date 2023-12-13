package com.example.mealplanner.view.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityHelpSettingBinding;
import com.example.mealplanner.databinding.ActivityPrivacyPolicyBinding;
import com.example.mealplanner.model.InfoPageModel;
import com.example.mealplanner.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PrivacyPolicyActivity extends BaseActivity {

    private ActivityPrivacyPolicyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sample data for pages
        List<InfoPageModel> infoPagesList = new ArrayList<>();
        infoPagesList.add(new InfoPageModel("Information Collection:", "The Meal Planner app collects user data such as recipes saved, meal plans created, and user preferences. This information is used to enhance the user experience and improve app functionality."));
        infoPagesList.add(new InfoPageModel("Data Security:","We prioritize the security of your data. The Meal Planner app employs industry-standard security measures to protect against unauthorized access, disclosure, or alteration of user information."));
        infoPagesList.add(new InfoPageModel("Third-Party Services:","The app may integrate with third-party services for enhanced features. Users are encouraged to review the privacy policies of these services as the Meal Planner app is not responsible for their practices."));
        infoPagesList.add(new InfoPageModel("Updates to Privacy Policy:","The Meal Planner app may update its Privacy Policy periodically. Users will be notified of any changes, and continued use of the app implies acceptance of the updated policy."));
        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewInfoPages);
        InfoPagesAdapter infoPagesAdapter = new InfoPagesAdapter(infoPagesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(infoPagesAdapter);

        //Set up cancel button
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
