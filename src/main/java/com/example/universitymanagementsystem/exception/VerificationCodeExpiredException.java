package com.example.universitymanagementsystem.exception;

public class VerificationCodeExpiredException extends Exception{
    public VerificationCodeExpiredException(String message) {
        super(message);
    }
}
