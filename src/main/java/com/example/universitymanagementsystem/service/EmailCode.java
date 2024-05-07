package com.example.universitymanagementsystem.service;

public interface EmailCode {
    void createCode(String email,String code);
    void compare(String email,String code);

}
