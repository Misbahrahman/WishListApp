package com.example.wishlistApp.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

// Setting the field defaults to private access level
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class UserDto {
    String username; // Username of the user
    String password; // Password of the user
}
