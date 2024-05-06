package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.repository.VerificationCodeRepository;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import com.example.universitymanagementsystem.service.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ApplicantApplicationServiceImpl implements ApplicantApplicationService {
    private final VerificationCodeRepository verificationCodeRepository;
    private final ApplicantApplicationRepository applicationRepository;
    private final SpecialtyAdmissionRepository specialtyAdmissionRepository;
    private final EmailService emailService;

    public ApplicantApplicationServiceImpl(
            VerificationCodeRepository verificationCodeRepository,
            ApplicantApplicationRepository appRep,
            SpecialtyAdmissionRepository specialtyAdmissionRepository, EmailService emailService) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.applicationRepository = appRep;
        this.specialtyAdmissionRepository = specialtyAdmissionRepository;
        this.emailService = emailService;
    }

    @Override
    public Long registerApplicantApplication(ApplicantApplication app) throws
            ApplicantApplicationAlreadyExistException,
            InvalidVerificationCodeExpcetion,
            VerificationCodeExpiredException,
            ApplicantApplicationAlreadyAppliedException,
            SpecialtyAdmissionInvalidException, MessagingException {
//        VerificationCode verificationCode = verificationCodeRepository
//                .findByOwnerPersonalNumber(app.getPersonalNumber())
//                .orElseThrow(() -> new InvalidVerificationCodeExpcetion("Invalid verification code"));
//        if(verificationCode.getIsApplied()){
//            throw  new ApplicantApplicationAlreadyAppliedException("Applicant application already applied");
//        }
//        if(LocalDateTime.now().isBefore(verificationCode.getExpireDate())){
//            throw new VerificationCodeExpiredException("Verification code expired");
//        }
//       specialtyAdmissionRepository
//                .getActiveBySpecialtyId(app.getSpecialty().getId())
//                .orElseThrow(() -> new SpecialtyAdmissionInvalidException("Specialty admission invalid"));
//        applicationRepository
//                .findByPersonalNumberAndStatus(app.getPersonalNumber(), "created")
//                .orElseThrow(() -> new ApplicantApplicationAlreadyExistException("Applicant application already exist and waiting for verification"));

        emailService.sendMessage(app.getEmail(), "Verification", generateCode());

        return applicationRepository.save(app).getId();

    }

    private String generateCode(){
        return Integer.toString(new Random().nextInt(100000, 999999));
    }
}
