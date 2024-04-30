package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplicationStatus;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.repository.VerificationCodeRepository;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplicantApplicationServiceImpl implements ApplicantApplicationService {
    private final VerificationCodeRepository verificationCodeRepository;
    private final ApplicantApplicationRepository applicationRepository;
    private final SpecialtyAdmissionRepository specialtyAdmissionRepository;

    public ApplicantApplicationServiceImpl(
            VerificationCodeRepository verificationCodeRepository,
            ApplicantApplicationRepository appRep,
            SpecialtyAdmissionRepository specialtyAdmissionRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.applicationRepository = appRep;
        this.specialtyAdmissionRepository = specialtyAdmissionRepository;
    }

    @Override
    public Long registerApplicantApplication(ApplicantApplication app) throws
            ApplicantApplicationAlreadyExistException,
            InvalidVerificationCodeExpcetion,
            VerificationCodeExpiredException,
            ApplicantApplicationAlreadyAppliedException,
            SpecialtyAdmissionInvalidException
    {
        VerificationCode verificationCode = verificationCodeRepository
                .findByOwnerPersonalNumber(app.getPersonalNumber())
                .orElseThrow(() -> new InvalidVerificationCodeExpcetion("Invalid verification code"));
        if(verificationCode.getIsApplied()){
            throw  new ApplicantApplicationAlreadyAppliedException("Applicant application already applied");
        }
        if(LocalDateTime.now().isBefore(verificationCode.getExpireDate())){
            throw new VerificationCodeExpiredException("Verification code expired");
        }
        specialtyAdmissionRepository
                .getActiveBySpecialtyId(app.getSpecialty().getId())
                .orElseThrow(() -> new SpecialtyAdmissionInvalidException("Specialty admission invalid"));
        applicationRepository
                .findByPersonalNumberAndStatus(app.getPersonalNumber(), ApplicantApplicationStatus.CREATED)
                .orElseThrow(() -> new ApplicantApplicationAlreadyExistException("Applicant application already exist and waiting for verification"));

        return applicationRepository.save(app).getId();

    }
}
