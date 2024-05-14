package com.example.universitymanagementsystem.service;

public interface ApplicantVerificationFacade {

    void transferOfApplicantToCandidate(Long id, String message, boolean verify);
}
