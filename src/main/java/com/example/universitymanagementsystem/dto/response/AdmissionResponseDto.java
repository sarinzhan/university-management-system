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
    private String facultyName;
    private LocalDateTime createdDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer groupAmount;
    private Integer groupCapacity;
    private Integer minScore;
}
