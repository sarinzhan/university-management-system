package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(BaseBusinessLogicException.class)
    public CommonResponseDto<Void> handleBaseException(BaseBusinessLogicException ex){
        CommonResponseDto<Void> responseDto = new CommonResponseDto<>();
        responseDto.setStatus(400);
        responseDto.setMessage(ex.getMessage());
        return responseDto;
    }

    @ExceptionHandler(Exception.class)
    public CommonResponseDto<Void> handleException(){
        CommonResponseDto<Void> responseDto = new CommonResponseDto<>();
        responseDto.setStatus(500);
        responseDto.setMessage("Internal server error");
        return responseDto;
    }
}
