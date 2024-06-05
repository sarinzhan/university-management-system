package com.example.universitymanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "information about specialty")
public class SpecialtyResponseDto {
    private Long specialtyId;
    private String specialtyName;
    private Long departmentId;
    private String departmentName;
    private String degree;
    private String educationForm;
    private String numberOfSemesters;
}
