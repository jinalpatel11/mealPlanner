package com.example.mealplanner.view.setting;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.controller.UserController;
import com.example.mealplanner.databinding.ActivityMyProfileBinding;
import com.example.mealplanner.databinding.ActivityRegistrationBinding;
import com.example.mealplanner.databinding.ActivitySettingBinding;
import com.example.mealplanner.model.User;
import com.example.mealplanner.repositories.UserRepository;
import com.example.mealplanner.view.BaseActivity;
import com.example.mealplanner.view.auth.LoginActivity;
import com.example.mealplanner.view.auth.RegistrationActivity;
import com.example.mealplanner.view.home.HomeActivity;
import com.example.mealplanner.view.home.SettingActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyProfileActivity extends BaseActivity {

    private ActivityMyProfileBinding binding;

    private  User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setupCancelButton();


        String email = getUserEmailFromSession();
        Log.d("MyProfileActivity", "Email: " + email);

        User user = userController.getUserByEmail(email);
        Log.d("MyProfileActivity", "User: " + user);
        setupProfileData(user);

        // Set OnClickListener for each field TextView
        setFieldClickListener(R.id.firstNameTextView);
        setFieldClickListener(R.id.lastNameTextView);
        setFieldClickListener(R.id.heightTextView);
        setFieldClickListener(R.id.weightTextView);

        setFieldClickListener(R.id.sexTextView);

        setFieldClickListener(R.id.birthdayTextView);
    }

    private void setupProfileData(User user) {
        if (user != null) {
            this.user = user;

            // Set first name
            if (user.getFirstName() != null) {
                binding.firstNameTextView.setText(user.getFirstName());
            } else {
                // Handle null value for first name
                binding.firstNameTextView.setText("N/A");
            }

            // Set last name
            if (user.getLastName() != null) {
                binding.lastNameTextView.setText(user.getLastName());
            } else {
                // Handle null value for last name
                binding.lastNameTextView.setText("N/A");
            }

            // Set sex
            if (user.getSex() != null) {
                binding.sexTextView.setText(user.getSex());
            } else {
                // Handle null value for sex
                binding.sexTextView.setText("N/A");
            }

            // Set birthday
            if (user.getBirthdate() != null) {
                binding.birthdayTextView.setText(user.getBirthdate());
            } else {
                // Handle null value for birthday
                binding.birthdayTextView.setText("N/A");
            }

            // Set height
            if (user.getHeight() != null) {
                binding.heightTextView.setText(user.getHeight());
            } else {
                // Handle null value for height
                binding.heightTextView.setText("N/A");
            }

            // Set weight
            if (user.getWeight() != null) {
                binding.weightTextView.setText(user.getWeight());
            } else {
                // Handle null value for weight
                binding.weightTextView.setText("N/A");
            }

            // Set activity level
            if (user.getActivityLevel() != null) {
                binding.activityLevelTextView.setText(user.getActivityLevel());
            } else {
                // Handle null value for activity level
                binding.activityLevelTextView.setText("N/A");
            }

            // Add other fields as needed
        } else {
            // Handle the case where the user does not exist
            Log.e("MyProfileActivity", "User is null");
        }
    }



    private void redirectToSetting() {
        Intent settingIntent = new Intent(MyProfileActivity.this, SettingActivity.class);
        startActivity(settingIntent);
        finish();
    }


    private void setFieldClickListener(int fieldTextViewId) {


        TextView fieldTextView = findViewById(fieldTextViewId);
        fieldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fieldTextViewId == R.id.sexTextView)
                {
                    showUpdateSexDialog(fieldTextViewId);
                }else if(fieldTextViewId == R.id.birthdayTextView)
                {
                    showUpdateDateDialog();
                }
                else
                {
                    showUpdateFieldDialog(fieldTextViewId);
                }


            }
        });
    }

    private void showUpdateFieldDialog(int fieldTextViewId) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_field);

        // Add your logic to handle updating the field in the dialog

        TextView fieldValueTextView = dialog.findViewById(R.id.fieldEditText);
        fieldValueTextView.setText(getFieldValue(fieldTextViewId)); // Get the current field value

        Button updateButton = dialog.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the update logic here
                // Close the dialog after updating
                EditText editText = dialog.findViewById(R.id.fieldEditText);
                String value = editText.getText().toString();
                String email = getUserEmailFromSession();
                User user = userController.getUserByEmail(email);

                if (fieldTextViewId == R.id.firstNameTextView) {
                    user.setFirstName(value);
                } else if (fieldTextViewId == R.id.lastNameTextView) {
                    user.setLastName(value);
                } else if (fieldTextViewId == R.id.heightTextView) {
                    user.setHeight(value);
                } else if (fieldTextViewId == R.id.weightTextView) {
                    user.setWeight(value);
                }

                // Update the user
                boolean isUpdateSuccessful = userController.updateUserModel(user);

                if (isUpdateSuccessful) {
                    // Update successful
                    // Update the UI with the new user information
                    setupProfileData(user);

                } else {
                    // Update failed (user with the specified email might not exist)
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showUpdateSexDialog(int fieldTextViewId) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_sex);

        // Add your logic to handle updating the field in the dialog

        RadioGroup radioGroup = dialog.findViewById(R.id.sexRadioGroup);
        String currentSex = getFieldValue(fieldTextViewId);

        // Set the current selection based on the current sex value
        if ("Male".equals(currentSex)) {
            radioGroup.check(R.id.radioButtonMale);
        } else if ("Female".equals(currentSex)) {
            radioGroup.check(R.id.radioButtonFemale);
        } else {
            // Handle other cases or set a default selection
        }

        Button updateButton = dialog.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the update logic here
                // Close the dialog after updating
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String value = "";

                if (selectedId == R.id.radioButtonMale) {
                    value = "Male";
                } else if (selectedId == R.id.radioButtonFemale) {
                    value = "Female";
                } else {
                    // Handle other cases or set a default value
                }

                String email = getUserEmailFromSession();
                User user = userController.getUserByEmail(email);

                // Update the user's sex
                user.setSex(value);

                // Update the user
                boolean isUpdateSuccessful = userController.updateUserModel(user);

                if (isUpdateSuccessful) {
                    // Update successful
                    // Update the UI with the new user information
                    setupProfileData(user);

                } else {
                    // Update failed (user with the specified email might not exist)
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private String getFieldValue(int fieldTextViewId) {
        if (fieldTextViewId == R.id.firstNameTextView) {
            return user.getFirstName();
        } else if (fieldTextViewId == R.id.lastNameTextView) {
            return user.getLastName();
        } else if (fieldTextViewId == R.id.heightTextView) {
            return user.getHeight();
        } else if (fieldTextViewId == R.id.weightTextView) {
            return user.getWeight();
        }
        // Add similar conditions for other fields
        else {
            // Handle unknown field ID (should not happen in a well-defined system)
            return "Unknown Field";
        }
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


    private void showUpdateDateDialog() {
        // Get the current date from the user object or use a default date
        String currentDate = user != null ? user.getBirthdate() : getCurrentDate();

        // Parse the current date to get year, month, and day
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date;
        Calendar calendar = Calendar.getInstance();
        try {
            date = dateFormat.parse(currentDate);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                // Handle the selected date
                String formattedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);

                // Update the user object
                if (user != null) {
                    user.setBirthdate(formattedDate);

                    // Update the user in the database
                    boolean isUpdateSuccessful = userController.updateUserModel(user);

                    if (isUpdateSuccessful) {
                        // Update successful
                        // Update the UI with the new user information
                        setupProfileData(user);
                    } else {
                        // Update failed (user with the specified email might not exist)
                    }
                }


            }
        }, year, month, day);

        // Show the dialog
        datePickerDialog.show();
    }

    // Helper method to get the current date in the "yyyy-MM-dd" format
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());
    }

}


