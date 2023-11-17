package com.example.mealplanner.repositories;

// UserRepository.java
import com.example.mealplanner.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean isUserExist(String username) {
        return users.containsKey(username);
    }

    public boolean isPasswordCorrect(String username, String password) {
        User user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }
}

