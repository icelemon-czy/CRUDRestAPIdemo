package com.crudrestapidemo.service.impl;

import com.crudrestapidemo.dto.UserDto;
import com.crudrestapidemo.entity.User;
import com.crudrestapidemo.mapper.UserMapper;
import com.crudrestapidemo.repository.UserRepository;
import com.crudrestapidemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    // We have a UserServiceImpl as a spring bean and this Spring Bean has only one parameterized constructor
    // @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA Entity
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        // Convert User JPA entity into UserDto
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return UserMapper.mapToUserDto(optionalUser.get());
    }
    @Override
    public List<UserDto> getAllUsers(){
        List<User> users =  userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
