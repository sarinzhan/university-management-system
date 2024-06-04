package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.User;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.UserRepository;
import com.example.universitymanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByLogin(username);
    }

    @Override
    public User save(User user) {
        try{
           return  userRepository.save(user);
        }catch (Exception ex) {
            throw new BaseBusinessLogicException("Не удалось сохранить пользователя");
        }
    }
}
