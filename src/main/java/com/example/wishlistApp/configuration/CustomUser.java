package com.example.wishlistApp.configuration;

import com.example.wishlistApp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

// This class implements the UserDetails interface provided by Spring Security.
public class CustomUser implements UserDetails {

    private User user;

    // Constructor that takes a User object as parameter.
    public CustomUser(User user) {
        super();
        this.user = user;
    }

    // Method to retrieve the authorities (roles) assigned to the user.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Creating a SimpleGrantedAuthority with the role "ROLE_USER".
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        // Returning a list containing the granted authority.
        return Arrays.asList(simpleGrantedAuthority);
    }

    // Method to retrieve the user's password.
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // Method to retrieve the user's username.
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    // Method to check if the user's account is non-expired.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Method to check if the user's account is non-locked.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Method to check if the user's credentials are non-expired.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Method to check if the user's account is enabled.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
