package com.example.wishlistApp.transformer;

import com.example.wishlistApp.dto.ItemDto;
import com.example.wishlistApp.dto.ItemResponseDto;
import com.example.wishlistApp.model.Item;

public class ItemTransformer {

    // Method to transform ItemDto to Item entity
    public static Item itemDtoToItem(ItemDto itemDto){
        // Building and returning an Item entity
        return Item.builder()
                .productName(itemDto.getProductName())
                .productCategory(itemDto.getProductCategory())
                .build();
    }

    // Method to transform Item entity to ItemResponseDto
    public static ItemResponseDto itemToitemResponseDto(Item item){
        // Building and returning an ItemResponseDto
        return ItemResponseDto.builder()
                .id(item.getId())
                .userName(item.getUser().getUserName()) // Assuming you have a User field in Item entity
                .productCategory(item.getProductCategory())
                .productName(item.getProductName())
                .build();
    }
}
