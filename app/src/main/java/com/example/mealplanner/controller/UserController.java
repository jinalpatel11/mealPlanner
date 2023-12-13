package com.example.mealplanner.controller;

import android.app.Activity;
import android.content.Intent;

import com.example.mealplanner.model.User;
import com.example.mealplanner.repositories.UserRepository;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

// UserController.java
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUserEmailIdExist(String email) {
       return userRepository.isUserEmailIdExist(email);
    }

    public boolean register(User user) {
        if (!userRepository.isUserEmailIdExist(user.getEmail())) {
            userRepository.addUser(user);
            return true; // Registration successful
        }
        return false; // User already exists
    }

    public boolean signIn(String email, String password) {
        return userRepository.isPasswordCorrect(email, password);
    }

    public  User getUserByEmail(String email){
        return userRepository.getUser(email);
    }

    public Boolean updateUserModel(User user){
        return userRepository.updateUser(user);
    }


    public boolean updateUserProfilePicture(String userEmail, byte[] photoData) {
        // Get the existing user from the repository
        User user = userRepository.getUser(userEmail);

        if (user != null) {
            // Update the user's profile picture
            user.setPhotoData(photoData);

            // Update the user in the repository
            return userRepository.updateUser(user);
        }

        return false; // User not found
    }

}
