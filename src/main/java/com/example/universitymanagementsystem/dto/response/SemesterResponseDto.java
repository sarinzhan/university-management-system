package com.example.universitymanagementsystem.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SemesterResponseDto {
    private Long semesterId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long curriculumId;
    private Integer number;
}
