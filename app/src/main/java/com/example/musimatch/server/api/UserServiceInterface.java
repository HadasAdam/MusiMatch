package com.example.musimatch.server.api;

import com.example.musimatch.models.User;

public interface UserServiceInterface {
    User getUserById(Long id);
    User getUserByUsername(String username);
    void updateUser(User user);
}
