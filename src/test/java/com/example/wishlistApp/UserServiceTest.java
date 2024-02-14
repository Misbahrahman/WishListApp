package com.example.wishlistApp;

import com.example.wishlistApp.dto.UserDto;
import com.example.wishlistApp.exception.CustomException;
import com.example.wishlistApp.model.User;
import com.example.wishlistApp.repository.UserRepo;
import com.example.wishlistApp.transformer.UserTranformer;
import com.example.wishlistApp.service.UserService;
import com.example.wishlistApp.transformer.UserTranformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser() {
        // Creating a mock UserDto
        UserDto userDto = UserDto.builder().username("testuser").password("testpassword").build();

        // Creating a mock User entity to be saved
        User userToSave = UserTranformer.transformUserDtoToUser(userDto);

        // Mocking the behavior of userRepo.save method
        when(userRepo.save(any(User.class))).thenReturn(userToSave);

        // Calling the method to be tested
        UserDto result = userService.registerUser(userDto);

        // Verifying that the save method was called with the correct parameters
        verify(userRepo, times(1)).save(any(User.class));

        // Asserting that the result is not null
        assertNotNull(result);

        // Asserting that the result is equal to the input userDto
        assertEquals(userDto.getUsername(), result.getUsername());
    }




    //Creates User and check Repo functions and services and Check if duplicates can be injected(All in one)
    @Test
    public void testRegisterUser_UserExists() {
        // Creating a mock UserDto
        UserDto userDto = UserDto.builder().username("testuser").password("testpassword").build();

        // Mocking the behavior of userRepo.existsById method to return true (indicating user already exists)
        when(userRepo.existsById(anyString())).thenReturn(true);

        // Calling the method to be tested and verifying that it throws CustomException
        assertThrows(CustomException.class, () -> userService.registerUser(userDto));

        // Verifying that the existsById method was called
        verify(userRepo, times(1)).existsById(anyString());
        // Verifying that the save method was not called
        verify(userRepo, never()).save(any(User.class));
    }
}
