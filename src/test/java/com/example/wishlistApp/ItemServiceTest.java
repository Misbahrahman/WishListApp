package com.example.wishlistApp;

import com.example.wishlistApp.dto.ItemDto;
import com.example.wishlistApp.dto.ItemResponseDto;
import com.example.wishlistApp.exception.CustomException;
import com.example.wishlistApp.model.Item;
import com.example.wishlistApp.model.User;
import com.example.wishlistApp.repository.ItemRepo;
import com.example.wishlistApp.repository.UserRepo;
import com.example.wishlistApp.service.ItemService;
import com.example.wishlistApp.transformer.ItemTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.security.core.Authentication;

public class ItemServiceTest {

    @Mock
    private ItemRepo itemRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllItems() {
        // Set up a mock authentication context with a logged-in user
        setUpMockAuthenticationContext("testuser");

        // Mocking the behavior of itemRepo.findByUserUserName method
        when(itemRepo.findByUserUserName(anyString())).thenReturn(new ArrayList<>());

        //testing getAll method
        List<ItemResponseDto> result = itemService.getAllItems();

        // testing findByUserUserName method
        verify(itemRepo, times(1)).findByUserUserName(anyString());

        // Verifying the result
        assertEquals(0, result.size());
    }

    //todo-TestTOcheckAddItems

    @Test
    public void testDeleteItem_ItemNotFound() {
        // Mocking the behavior of itemRepo.findById method
        when(itemRepo.findById(anyInt())).thenReturn(Optional.empty());

        // Calling the method to be tested and verifying that it throws CustomException
        assertThrows(CustomException.class, () -> itemService.deleteItem(1));

        // Verifying that the findById method was called
        verify(itemRepo, times(1)).findById(anyInt());
    }

    //method to set up a mock authentication context with a logged-in user
    private void setUpMockAuthenticationContext(String username) {
        User user = User.builder().userName(username).password("password").build();
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(authentication.getName()).thenReturn(username);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}
