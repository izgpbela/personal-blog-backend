package com.example.personalblogbackend.service;

import com.example.personalblogbackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    Optional<User> getUserByEmail(String email);
}
