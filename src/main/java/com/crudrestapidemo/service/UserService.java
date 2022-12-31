package com.crudrestapidemo.service;

import com.crudrestapidemo.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long id);
}
