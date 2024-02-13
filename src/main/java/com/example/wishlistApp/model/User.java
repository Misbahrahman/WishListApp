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
public class User {

    @Id
    String userName; // Username of the user, used as the primary key

    String password; // Password of the user

    // Since I would be adding only One role ie User , No need to define this
    // String role;
}
