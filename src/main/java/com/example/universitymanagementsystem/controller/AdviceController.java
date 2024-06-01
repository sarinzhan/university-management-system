package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(BaseBusinessLogicException.class)
    public CommonResponseDto<Void> handleBaseException(BaseBusinessLogicException ex){
        CommonResponseDto<Void> responseDto = new CommonResponseDto<>();
        responseDto.setStatus(400);
        responseDto.setMessage(ex.getMessage());
        return responseDto;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponseDto<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new CommonResponseDto<Map<String, String>>()
                .setMessage(errors)
                .setStatus(400);
    }
}
