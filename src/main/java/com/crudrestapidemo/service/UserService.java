package com.crudrestapidemo.service;

import com.crudrestapidemo.dto.UserDto;
import com.crudrestapidemo.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
}
