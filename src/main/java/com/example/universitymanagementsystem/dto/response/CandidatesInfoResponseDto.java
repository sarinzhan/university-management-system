package com.example.universitymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatesInfoResponseDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer testScore;
    private Boolean isAdmitted;
}
