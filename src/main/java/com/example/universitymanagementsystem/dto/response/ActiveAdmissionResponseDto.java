package com.example.universitymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveAdmissionResponseDto {
    private Long admissionId;
    private Integer groupCapacity;
    private Integer groupAmount;
    private Integer minScore;
    private String specialtyName;
    private String facultyName;
    private String departmentName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;
}
