package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.dto.request.LoginRequestDto;
import com.example.universitymanagementsystem.dto.response.TokenResponseDto;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.security.JwtTokenHandler;
import com.example.universitymanagementsystem.service.AuthService;
import com.example.universitymanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenHandler jwtTokenHandler;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDto authenticateUser(LoginRequestDto cred){
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        UserDetails userDetails = userService.loadUserByUsername(cred.getLogin());
        if(Objects.nonNull(userDetails) &&
                passwordEncoder.matches(cred.getPassword(),userDetails.getPassword()) &&
                !userDetails.getAuthorities().isEmpty()
        ){
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            tokenResponseDto.setToken( jwtTokenHandler.generateToken(auth));
            String authority = userDetails.getAuthorities().stream().toList().get(0).getAuthority();
            tokenResponseDto.setRole(authority);
        }else{
            throw new BaseBusinessLogicException("Ошибка авторизации");
        }
        return tokenResponseDto;
    }
}
