package com.example.universitymanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Response to successful applicant application registration")
@Data
public class RegisterApplicantResponseDto {
    private Long applicantApplicationId;
}
