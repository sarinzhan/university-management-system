package com.example.universitymanagementsystem.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CurriculumResponseDto {
    private Long curriculumId;
    private Integer semesterNumber;
    private String identifierName;
    private LocalDate createdDate;
    private Long specialtyId;
    private String specialtyName;
}
