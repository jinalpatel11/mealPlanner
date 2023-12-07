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

    public User getUser(String username) {
        return databaseHelper.getUser(username);
    }

    public boolean isUserEmailIdExist(String email) {
        return databaseHelper.isEmailExist(email);
    }

    public boolean isPasswordCorrect(String email, String password) {
        return databaseHelper.isPasswordCorrect(email, password);
    }
}
