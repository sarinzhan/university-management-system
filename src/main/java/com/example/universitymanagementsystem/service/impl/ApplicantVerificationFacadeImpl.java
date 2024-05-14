package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.service.ApplicantVerificationFacade;
import com.example.universitymanagementsystem.mapper.ApplicantApplicationMapper;
import com.example.universitymanagementsystem.service.impl.ApplicantApplicationServiceImpl;
import com.example.universitymanagementsystem.service.impl.CandidateServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicantVerificationFacadeImpl implements ApplicantVerificationFacade {
    private final ApplicantApplicationServiceImpl applicantApplicationService;
    private final CandidateServiceImpl candidateService;
    private final ApplicantApplicationMapper applicantApplicationMapper;

    @Override
    public void transferOfApplicantToCandidate(Long id, String message, boolean verify) {
        this.candidateService.addCandidate(
                applicantApplicationMapper.applicantApplicationToCandidate(
                        applicantApplicationService.verificationOfApplicantApplication(id, message, verify)
                )
        );
    }
}

