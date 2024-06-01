package com.example.universitymanagementsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ApplicantApplicationVerifyRequestDto{
    @NotNull
    private Long applicantApplicationId;

    private String message;

    @NotNull
    private Boolean verify;
}