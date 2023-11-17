package com.example.mealplanner.controller;

import com.example.mealplanner.model.User;
import com.example.mealplanner.repositories.UserRepository;

// UserController.java
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String username, String password) {
        if (!userRepository.isUserExist(username)) {
            User user = new User(username, password);
            userRepository.addUser(user);
            return true; // Registration successful
        }
        return false; // User already exists
    }

    public boolean signIn(String username, String password) {
        return userRepository.isPasswordCorrect(username, password);
    }
}
