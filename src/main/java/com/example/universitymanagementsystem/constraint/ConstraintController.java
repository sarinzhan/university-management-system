package com.example.universitymanagementsystem.constraint;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
@RestControllerAdvice
public class ConstraintController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponseDto<List<String>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        String error = ex.getBindingResult().getAllErrors()
                .stream()
                .findFirst()
                .map(e -> e.getDefaultMessage())
                .orElse("Ошибка валидации");
        return new CommonResponseDto<List<String>>()
                .setMessage(error)
                .setStatus(400);
    }
}
