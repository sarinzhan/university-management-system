package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.VerificationCodeRepository;
import com.example.universitymanagementsystem.service.VerificationCodeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final ApplicantApplicationRepository applicationRepository;

    public VerificationCodeServiceImpl(VerificationCodeRepository verificationCodeRepository, ApplicantApplicationRepository applicationRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.applicationRepository = applicationRepository;
    }


    @Override
    @Transactional
    public Boolean verificateApplicantApplication(VerificationCode verificationCode) throws Exception {
         VerificationCode currentVerificationCode = verificationCodeRepository
                 .getByApplicantApplicationId(verificationCode.getApplicantApplicationId())
                 .orElseThrow(() -> new Exception("sdadas"));

         if (!currentVerificationCode.getCode().equals(verificationCode.getCode())) {
             new Exception("dasdasd");
         }

         if (LocalDateTime.now().isAfter(currentVerificationCode.getExpireDate())){
             new Exception("asdasdas");
         }

        applicationRepository.findById(verificationCode
                .getApplicantApplicationId())
                .orElseThrow(() -> new Exception("asdasd"))
                .setIsActivated(true);

         currentVerificationCode.setIsApplied(true);

         return true;
    }
}
