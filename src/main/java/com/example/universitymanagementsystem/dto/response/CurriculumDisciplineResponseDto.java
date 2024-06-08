package com.example.universitymanagementsystem.dto.response;

import lombok.Data;

@Data
public class CurriculumDisciplineResponseDto {
    private Long curriculumDisciplineId;
    private Long disciplineId;
    private String disciplineName;
    private Long curriculumId;
    private Integer hour;
}
