package com.example.wishlistApp.controller;

import com.example.wishlistApp.dto.ItemDto;
import com.example.wishlistApp.dto.ItemResponseDto;
import com.example.wishlistApp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a controller that handles RESTful requests and returns data formatted as JSON
@RequestMapping("/item") // Base path for all endpoints in this controller
public class ItemController {

    @Autowired
    ItemService itemService; // Autowiring the ItemService

    // Endpoint to add a new item
    @PostMapping("/addItem")
    public ResponseEntity addItem(@RequestBody ItemDto itemDto){
        try {
            // Attempt to add the item
            ItemResponseDto item = itemService.addItem(itemDto);
            // Return a success response with the added item data
            return new ResponseEntity(item , HttpStatus.FOUND);
        }catch (Exception e){
            // If an exception occurs during item addition, return a bad request response with the error message
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to retrieve all items
    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        try {
            // Attempt to retrieve all items
            List<ItemResponseDto> list = itemService.getAllItems();
            // Return a success response with the list of items
            return new ResponseEntity(list , HttpStatus.FOUND);
        }catch (Exception e){
            // If an exception occurs during item retrieval, return a bad request response with the error message
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to delete an item by its ID
    @DeleteMapping("/deleteItem")
    public ResponseEntity deleteItem(@RequestParam int id){
        try {
            // Attempt to delete the item
            String message = itemService.deleteItem(id);
            // Return a success response with a message indicating the deletion
            return new ResponseEntity(message , HttpStatus.FOUND);
        }catch (Exception e){
            // If an exception occurs during item deletion, return a bad request response with the error message
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
