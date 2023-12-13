package com.example.mealplanner.view.setting;


import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyProfileActivity extends BaseActivity {

    private ActivityMyProfileBinding binding;

    private static final int REQUEST_IMAGE_CAPTURE = 101;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;


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

        setFieldClickListener(R.id.emailTextView);

        setFieldClickListener(R.id.profileImageView);
        setFieldClickListener(R.id.activityLevelTextView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Get the photo from the intent
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");

            // Update the user's profile picture using the captured photo
            if (user != null) {
                // Convert the Bitmap to a byte array (you may need to implement this conversion)
                byte[] photoBytes = convertBitmapToByteArray(photo);

                // Update the user's profile picture in the database
                boolean isUpdateSuccessful = userController.updateUserProfilePicture(user.getEmail(), photoBytes);

                if (isUpdateSuccessful) {
                    // Update successful
                    // Update the UI with the new user information, including the profile picture
                    setupProfileData(user);
                } else {
                    // Update failed (handle accordingly)
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                // Handle permission denied
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void setupProfileData(User user) {
        if (user != null) {
            this.user = user;

            setupUserInfo();


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

    private void setupUserInfo() {

        String userEmail =getUserEmailFromSession();

        User user = userController.getUserByEmail(userEmail);
        if(user.getPhotoData().length != 0)
        {
            Bitmap bitmap = BitmapFactory.decodeByteArray(user.getPhotoData(), 0, user.getPhotoData().length);
            binding.profileImageView.setImageBitmap(bitmap);

            // If available, set the ImageView with the image
            binding.profileImageView.setVisibility(View.VISIBLE);
            binding.emailTextView.setVisibility(View.GONE);



        }else
        {
            String userInitial = extractInitial(userEmail);
            binding.emailTextView.setText(userInitial);

            // If not available, show the TextView
            binding.profileImageView.setVisibility(View.GONE);
            binding.emailTextView.setVisibility(View.VISIBLE);


        }
    }

    private void setFieldClickListener(int fieldTextViewId) {
        View fieldView = findViewById(fieldTextViewId);

        if (fieldView instanceof TextView) {
            TextView fieldTextView = (TextView) fieldView;
            fieldTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle TextView click
                    if (fieldTextViewId == R.id.sexTextView) {
                        showUpdateSexDialog(fieldTextViewId);
                    } else if (fieldTextViewId == R.id.birthdayTextView) {
                        showUpdateDateDialog();
                    }else if(fieldTextViewId == R.id.activityLevelTextView){
                        showUpActivityLevelDialog();
                    }
                    else {
                        showUpdateFieldDialog(fieldTextViewId);
                    }
                }
            });
        } else if (fieldView instanceof ImageView) {
            ImageView fieldImageView = (ImageView) fieldView;
            fieldImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle ImageView click
                    if (fieldTextViewId == R.id.profileImageView) {
                        requestCameraPermission();
                    } else {
                        // Handle other ImageView clicks
                    }
                }
            });
        } else {
            // Handle other view types if needed
        }
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



    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            openCamera();
        }
    }


    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void showUpActivityLevelDialog() {
        // Create a dialog
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_activity_level);

        // Find views in the dialog layout
        SeekBar activityLevelSlider = dialog.findViewById(R.id.activityLevelSlider);
        TextView activityLevelValueTextView = dialog.findViewById(R.id.activityLevelValueTextView);
        Button updateButton = dialog.findViewById(R.id.updateButton);

        // Set the initial value of the slider and text view based on the user's current activity level
        int currentActivityLevel = getActivityLevelValue(user.getActivityLevel());
        activityLevelSlider.setProgress(currentActivityLevel);
        activityLevelValueTextView.setText(getActivityLevelText(currentActivityLevel));

        // Set up a listener for changes in the slider value
        activityLevelSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update the text view with the current activity level
                activityLevelValueTextView.setText(getActivityLevelText(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed for this implementation
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed for this implementation
            }
        });

        // Set up a listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the update logic here
                // Close the dialog after updating
                int selectedActivityLevel = activityLevelSlider.getProgress();

                // Update the user object
                if (user != null) {
                    user.setActivityLevel(getActivityLevelText(selectedActivityLevel));

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

                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }

    // Helper method to get the activity level text based on the slider progress
    private String getActivityLevelText(int progress) {
        switch (progress) {
            case 0:
                return "Sedentary";
            case 1:
                return "Lightly Active";
            case 2:
                return "Moderately Active";
            case 3:
                return "Very Active";
            case 4:
                return "Extremely Active";
            default:
                return "Unknown";
        }
    }

    // Helper method to get the activity level value based on the text
    private int getActivityLevelValue(String activityLevel) {
        switch (activityLevel) {
            case "Sedentary":
                return 0;
            case "Lightly Active":
                return 1;
            case "Moderately Active":
                return 2;
            case "Very Active":
                return 3;
            case "Extremely Active":
                return 4;
            default:
                return 0; // Default to Sedentary if the activity level is unknown
        }
    }


}


