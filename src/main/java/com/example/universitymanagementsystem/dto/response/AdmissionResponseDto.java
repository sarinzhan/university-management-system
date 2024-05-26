package com.example.universitymanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(description = "Admission into response dto, for committee")
public class AdmissionResponseDto {
    private String specialtyName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;
}
