package com.example.universitymanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Подтверждение заявки абитуриента")
public class ApplicantVerificationCodeDto {
    @NotNull
    private Long applicantApplicationId;

    @NotNull
    @Size(min = 7,message = "Код должен состоять из 7 символов")
    @Size(max = 7, message = "Код должен состоять из 7 символов")
    private String code;
}
