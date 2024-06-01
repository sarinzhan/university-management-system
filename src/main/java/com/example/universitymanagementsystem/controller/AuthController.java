package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.LoginRequestDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.TokenResponseDto;
import com.example.universitymanagementsystem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@Tag(name = "Authorization API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя")
    public CommonResponseDto<TokenResponseDto> loginUser(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ){
        CommonResponseDto<TokenResponseDto> responseDto = new CommonResponseDto<>();

        TokenResponseDto tokenResponseDto = authService.authenticateUser(loginRequestDto);
        if(Objects.nonNull(tokenResponseDto.getToken())) {
            return new CommonResponseDto<TokenResponseDto>().setData(tokenResponseDto).setOk();
        }
        responseDto.setMessage("Неправильный логин или пароль");
        responseDto.setStatus(400);
        return responseDto;
    }
}
