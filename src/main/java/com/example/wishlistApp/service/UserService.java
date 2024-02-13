package com.example.wishlistApp.service;

import com.example.wishlistApp.dto.UserDto;
import com.example.wishlistApp.exception.CustomException;
import com.example.wishlistApp.model.User;
import com.example.wishlistApp.repository.UserRepo;
import com.example.wishlistApp.transformer.UserTranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Indicates that this class is a service component in Spring
public class UserService {

    @Autowired
    UserRepo userRepo;

    // Method to register a new user
    public UserDto registerUser(UserDto userDto) {

        // Check if username already exists
        if (alreadyExist(userDto.getUsername()))
            throw new CustomException(userDto.getUsername() + " Already Exist , Try Making New One");

        // Check for any null values in userDto
        if (nullVal(userDto))
            throw new CustomException("Values Can't be null");

        // Transforming UserDto to User entity
        User toSave = UserTranformer.transformUserDtoToUser(userDto);

        // Saving the user to the database
        User savedUser = userRepo.save(toSave);

        // Transforming the saved User entity back to UserDto and returning it
        return UserTranformer.transformfromUserToUserDto(savedUser);
    }

    // Method to check if any field in UserDto is null
    private boolean nullVal(UserDto userDto) {
        return (userDto.getUsername() == null || userDto.getPassword() == null);
    }

    // Method to check if a user with the given username already exists
    private boolean alreadyExist(String username) {
        // Checking in the database if any user with the given username exists
        return (userRepo.existsById(username));
    }
}
