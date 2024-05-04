package com.example.universitymanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerFromFeature1 {
    @GetMapping
    public String getMa(){
        return "Hello";
    }
}
