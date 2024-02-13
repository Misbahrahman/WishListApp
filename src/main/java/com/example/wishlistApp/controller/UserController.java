package com.example.wishlistApp.controller;

import com.example.wishlistApp.dto.UserDto;
import com.example.wishlistApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Marks this class as a controller that handles RESTful requests and returns data formatted as JSON
public class UserController {

    @Autowired
    UserService userService;

    // Endpoint to register a new user
    @PostMapping("/register")
    ResponseEntity registerUser(@RequestBody UserDto userDto){

        try {
            // Attempt to register the user
            UserDto response = userService.registerUser(userDto);
            // Return a success response with the registered user data
            return new ResponseEntity(response , HttpStatus.OK);
        }catch (Exception e){
            // If an exception occurs during user registration, return a bad request response with the error message
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
