package com.example.universitymanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Information about admission")
public class AdmissionDetailsResponseDto {
    private String specialtyName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer groupAmount;
    private Integer seatNumber;
    private Integer requiredTestScore;
    private List<ApplicantCandidateResponseDto> applicantCandidates;
}
