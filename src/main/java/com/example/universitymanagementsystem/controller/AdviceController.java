package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import org.apache.commons.lang3.exception.UncheckedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(BaseBusinessLogicException.class)
    public CommonResponseDto<Void> handleBaseException(BaseBusinessLogicException ex){
        CommonResponseDto<Void> responseDto = new CommonResponseDto<>();
        responseDto.setStatus(400);
        responseDto.setMessage(ex.getMessage());
        return responseDto;
    }
}
