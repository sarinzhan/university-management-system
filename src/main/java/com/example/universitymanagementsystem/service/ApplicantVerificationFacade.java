package com.example.universitymanagementsystem.service;

public interface ApplicantVerificationFacade {

    Boolean transferOfApplicantToCandidate(Long applicantApplicationId, String reason, Boolean decision);
}
