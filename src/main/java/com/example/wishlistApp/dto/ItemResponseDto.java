package com.example.wishlistApp.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class ItemResponseDto {
    int id;
    String productName;
    String productCategory;
    String userName;
}
