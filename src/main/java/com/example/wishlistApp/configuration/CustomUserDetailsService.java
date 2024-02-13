package com.example.wishlistApp.configuration;

import com.example.wishlistApp.model.User;
import com.example.wishlistApp.repository.UserRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

// This class implements the UserDetailsService interface provided by Spring Security.
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    // Method to load user details by username.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Querying the UserRepository for a user with the provided username.
        Optional<User> response = userRepo.findById(username);
        // Checking if the user is found in the database.
        if(response.isPresent()){
            // Retrieving the user object from the optional.
            User user = response.get();
            // Creating and returning a CustomUser object with the retrieved user.
            return new CustomUser(user);
        }
        // Throwing UsernameNotFoundException if user is not found.
        else throw new UsernameNotFoundException("User not found");
    }
}
