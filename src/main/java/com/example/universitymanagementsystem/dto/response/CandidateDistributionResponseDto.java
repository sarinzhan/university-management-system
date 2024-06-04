package com.example.universitymanagementsystem.dto.response;

import lombok.Data;

@Data
public class CandidateDistributionResponseDto {
    private Long studentId;
    private String specialtyName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long groupId;
    private String groupName;
    private String testScore;
    private Long admissionId;
}
