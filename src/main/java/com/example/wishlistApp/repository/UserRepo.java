package com.example.wishlistApp.repository;

import com.example.wishlistApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this interface is a Spring Data repository
public interface UserRepo extends JpaRepository<User, String> {
}
