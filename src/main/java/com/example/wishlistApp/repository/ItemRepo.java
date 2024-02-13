package com.example.wishlistApp.repository;

import com.example.wishlistApp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indicates that this interface is a Spring Data repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

    // Method to find items by the username of the user who owns them
    // BreakDown -> it Does find username From User enttity
    List<Item> findByUserUserName(String loggedInUser);
}
