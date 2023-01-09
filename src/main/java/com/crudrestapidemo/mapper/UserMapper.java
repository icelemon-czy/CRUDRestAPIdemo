package com.crudrestapidemo.mapper;

import com.crudrestapidemo.dto.UserDto;
import com.crudrestapidemo.entity.User;

public class UserMapper {
    // Convert User JPA Entity to UserDto
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    // Convert User Dto to JPA Entity
    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }

}
