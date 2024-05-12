package com.example.universitymanagementsystem.dto.request;

import lombok.Data;

@Data
public class ApplicantApplicationVerifyRequestDto {
    private Long applicantApplication;
    private String message;
    private boolean verify;
}
