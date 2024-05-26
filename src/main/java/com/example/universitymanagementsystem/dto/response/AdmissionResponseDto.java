package com.example.universitymanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(description = "Get all the admissions for the specialties")
public class AdmissionResponceDto {
    private String specialtyName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;
}
