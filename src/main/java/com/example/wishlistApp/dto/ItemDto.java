package com.example.wishlistApp.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

// Setting the field defaults to private access level
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class ItemDto {
    //Just Using basic things as id and user is something automatically defined from instances
    String productName; // Name of the product
    String productCategory; // Category of the product
}
