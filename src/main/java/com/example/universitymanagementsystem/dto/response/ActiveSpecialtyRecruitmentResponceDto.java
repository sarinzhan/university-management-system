package com.example.universitymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveSpecialtyRecruitmentResponceDto {
    private Long specialtyId;
    private String specialtyName;
    private Integer seatsNumber;
}
