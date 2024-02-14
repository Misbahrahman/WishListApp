package com.example.wishlistApp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Getter
@Setter
@Builder
public class ItemResponseDto {
    int id;
    String productName;
    String productCategory;
    String userName;
}
