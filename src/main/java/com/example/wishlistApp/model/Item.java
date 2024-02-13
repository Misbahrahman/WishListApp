package com.example.wishlistApp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

// Setting the field defaults to private access level
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Item {

    @Id // Denotes the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generating the ID value automatically
    int id;

    String productName; // Name of the product

    String productCategory; // Category of the product

    @ManyToOne // Many-to-one relationship with User entity
    @JoinColumn // Specifies the foreign key column
    User user; // User who owns the item

}
