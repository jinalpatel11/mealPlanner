package com.example.mealplanner.repositories;

import android.content.Context;

import com.example.mealplanner.model.User;

public class UserRepository {

    private DatabaseHelper databaseHelper;

    public UserRepository(Context context) {
        // Initialize the DatabaseHelper in the constructor
        databaseHelper = new DatabaseHelper(context);
    }

    public long addUser(User user) {
        return databaseHelper.addUser(user);
    }

    public User getUser(String email) {
        return databaseHelper.getUser(email);
    }

    public boolean isUserEmailIdExist(String email) {
        return databaseHelper.isEmailExist(email);
    }

    public boolean isPasswordCorrect(String email, String password) {
        return databaseHelper.isPasswordCorrect(email, password);
    }

    // Method to update a user in the database
    public boolean updateUser(User updatedUser) {
        int rowsAffected = databaseHelper.updateUser(updatedUser);
        return rowsAffected > 0;
    }
}
