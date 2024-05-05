package com.example.universitymanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification-token")
public class VerificationController {
    @GetMapping("/check")
    public Boolean check(){
        return true;
    }
}
