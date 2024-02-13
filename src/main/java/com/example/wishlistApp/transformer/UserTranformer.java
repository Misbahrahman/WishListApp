package com.example.wishlistApp.transformer;

import com.example.wishlistApp.dto.UserDto;
import com.example.wishlistApp.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserTranformer {

    // Method to transform UserDto to User entity
    public static User transformUserDtoToUser(UserDto userDto){

        // Encrypting password using BCryptPasswordEncoder
        String encryptedPass = new BCryptPasswordEncoder().encode(userDto.getPassword());

        // Building and returning a User entity
        return User.builder()
                .userName(userDto.getUsername())
                .password(encryptedPass)
                .build();
    }

    // Method to transform User entity to UserDto
    public static UserDto transformfromUserToUserDto(User user){
        // Building and returning a UserDto
        return UserDto.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .build();
    }
}
