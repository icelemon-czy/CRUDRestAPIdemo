package com.crudrestapidemo.service.impl;

import com.crudrestapidemo.dto.UserDto;
import com.crudrestapidemo.entity.User;
import com.crudrestapidemo.exception.ResourceNotFoundException;
import com.crudrestapidemo.mapper.AutoUserMapper;
import com.crudrestapidemo.mapper.UserMapper;
import com.crudrestapidemo.repository.UserRepository;
import com.crudrestapidemo.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    // We have a UserServiceImpl as a spring bean and this Spring Bean has only one parameterized constructor
    // @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA Entity
        // User user = UserMapper.mapToUser(userDto);
        // User user = modelMapper.map(userDto,User.class);
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);
        // Convert User JPA entity into UserDto
        // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        // UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);

        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",userId)
        );
        // return UserMapper.mapToUserDto(optionalUser.get());
        // return modelMapper.map(optionalUser.get(),UserDto.class);
        return  AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users =  userRepository.findAll();
/*        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());*/
/*        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());*/
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("User","id", userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // return UserMapper.mapToUserDto(updatedUser);
        // return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User","id", id)
        );
        userRepository.deleteById(id);
    }

}
