package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;

import java.util.List;

public interface ApplicantApplicationService {
    Long registerApplicantApplication(ApplicantApplication app);
    List<ApplicantApplication> getEmailVerifiedApplicants();
}
