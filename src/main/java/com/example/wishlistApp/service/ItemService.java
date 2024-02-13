package com.example.wishlistApp.service;

import com.example.wishlistApp.dto.ItemDto;
import com.example.wishlistApp.dto.ItemResponseDto;
import com.example.wishlistApp.exception.CustomException;
import com.example.wishlistApp.model.Item;
import com.example.wishlistApp.model.User;
import com.example.wishlistApp.repository.ItemRepo;
import com.example.wishlistApp.repository.UserRepo;
import com.example.wishlistApp.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // Indicates that this class is a service component in Spring
public class ItemService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    UserRepo userRepo;

    //Predefined a Method to get the currently logged-in user
    String getCurrentlyLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    // Method to get all items belonging to the currently logged-in user
    public List<ItemResponseDto> getAllItems() {
        String loggedInUser = getCurrentlyLoggedInUser();
        List<ItemResponseDto> list = getResponseList(itemRepo.findByUserUserName(loggedInUser));
        if(list == null) // Throw custom exception if no items found
            throw new CustomException("No items Found");
        return list;
    }

    // Method to transform list of items to list of item response DTOs
    private List<ItemResponseDto>getResponseList(List<Item> items) {
        List<ItemResponseDto> list = new ArrayList<>();
        for(Item item : items){
            list.add(ItemTransformer.itemToitemResponseDto(item));
        }
        return list;
    }

    // Method to add an item
    public ItemResponseDto addItem(ItemDto itemDto) {
        String loggedInUser = getCurrentlyLoggedInUser();
        Item item = ItemTransformer.itemDtoToItem(itemDto);
        item.setUser(userRepo.findById(loggedInUser).get());
        Item savedItem = itemRepo.save(item);
        return ItemTransformer.itemToitemResponseDto(savedItem);
    }

    // Method to delete an item by its ID
    public String deleteItem(int id) {
        Optional<Item> response = itemRepo.findById(id);
        if(response.isPresent()){
            itemRepo.delete(response.get());
            return "Item with " + id+ " is Now Deleted";
        }
        else // Throw custom exception if item not found
            throw new CustomException("Item with " + id + " Doesn't exist");
    }
}
