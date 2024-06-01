package com.example.universitymanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "information about faculty")
public class FacultyResponseDto {
    private Long facultyId;
    private String facultyName;
}
