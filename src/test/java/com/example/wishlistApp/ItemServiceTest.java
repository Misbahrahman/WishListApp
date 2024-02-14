
package com.example.wishlistApp;

import com.example.wishlistApp.dto.ItemDto;
import com.example.wishlistApp.dto.ItemResponseDto;
import com.example.wishlistApp.dto.UserDto;
import com.example.wishlistApp.exception.CustomException;
import com.example.wishlistApp.model.Item;
import com.example.wishlistApp.model.User;
import com.example.wishlistApp.repository.ItemRepo;
import com.example.wishlistApp.repository.UserRepo;
import com.example.wishlistApp.service.ItemService;
import com.example.wishlistApp.service.UserService;
import com.example.wishlistApp.transformer.ItemTransformer;
import com.example.wishlistApp.transformer.UserTranformer;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    @Mock
    private ItemRepo itemRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private ItemService itemService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllItems() {
        setUpMockAuthenticationContext("MisbahTesting");

        //ANyString()-Items provide flexible string testing checks(could be any string , now check)
        // Mocking the behavior of itemRepo.findByUserUserName method
        when(itemRepo.findByUserUserName("MisbahTesting")).thenReturn(new ArrayList<>());

        //testing getAll method
        List<ItemResponseDto> result = itemService.getAllItems();

        // testing findByUserUserName method
        verify(itemRepo, times(1)).findByUserUserName(anyString());

        // Verifying the result
        assertEquals(0, result.size());
    }

    @Test
    public void testAddItem() {

        // Mock user
        User user = User.builder().userName("testuser").password("testpassword").build();

        //replicating userepo saving behaviour
        when(userRepo.findById("testuser")).thenReturn(Optional.of(user));

        // Mock item
        Item item = Item.builder()
                .productName("testProduct")
                .productCategory("testCategory")
                .build();

        //replicating itemRepo saving behaviour
        when(itemRepo.save(any(Item.class))).thenReturn(item);

        // Call the method to be tested
        ItemResponseDto result = itemService.addItemMappingUser(item, user);

        // Assertions
        assertEquals(result.getProductName(), item.getProductName());

        // Verify that save method was called
        verify(itemRepo, times(1)).save(any(Item.class));
    }



    @Test
    public void testDeleteItem() {
        // Mocking the behavior of itemRepo.findById method
        when(itemRepo.findById(anyInt())).thenReturn(Optional.of(new Item()));

        // Calling the method to be tested
        String result = itemService.deleteItem(1);

        // Verifying that the findById and delete methods were called
        verify(itemRepo, times(1)).findById(anyInt());
        verify(itemRepo, times(1)).delete(any(Item.class));

        // Verifying the result
        assertEquals("Item with 1 is Now Deleted", result);
    }

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
