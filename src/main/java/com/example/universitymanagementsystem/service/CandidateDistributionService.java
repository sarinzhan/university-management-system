package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.dto.response.CandidateDistributionResponseDto;

import java.util.List;

public interface CandidateDistributionService {
    List<CandidateDistributionResponseDto> distributeCandidates(Long admissionId);
}
