package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UserNotFoundException;
}
