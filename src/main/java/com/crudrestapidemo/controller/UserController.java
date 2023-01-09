package com.crudrestapidemo.controller;

import com.crudrestapidemo.dto.UserDto;
import com.crudrestapidemo.entity.User;
import com.crudrestapidemo.exception.ErrorDetails;
import com.crudrestapidemo.exception.ResourceNotFoundException;
import com.crudrestapidemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    // Build CreateUser Rest API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUserDto = userService.createUser(user);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }


    // Get User By id
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userdto = userService.getUserById(userId);
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }


    // Get All users
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }


    // Update User
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                           @RequestBody UserDto userDto){
        userDto.setId(id);
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }


    // Delete User
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("Successfully delete",HttpStatus.OK);
    }

    // Exception Handler to this specific User Controller
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "User Not Found"
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    }

}
