package com.example.universitymanagementsystem.dto.request;

import lombok.Data;

@Data
public class ApplicantVerificationCodeDto {
    private Long applicantApplicationId;
    private String code;
}
