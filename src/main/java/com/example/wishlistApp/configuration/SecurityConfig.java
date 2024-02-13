package com.example.wishlistApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean to provide custom user details service
    @Bean
    UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    // Bean to provide BCrypt password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean to provide DAO authentication provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    // Bean to configure security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disabling CSRF protection
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> // Configuring authorization rules
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/register").permitAll() // Allowing access to "/register" endpoint without authentication
                                .anyRequest().hasAnyRole("USER")) // Requiring authenticated users with role "USER" for all other requests
                .httpBasic(Customizer.withDefaults()); // Configuring HTTP Basic authentication

        return http.build();
    }
}
