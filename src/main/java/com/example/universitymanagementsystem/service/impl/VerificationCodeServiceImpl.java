package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.exception.ApplicantApplicationNotFoundException;
import com.example.universitymanagementsystem.exception.VerificationCodeException;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.VerificationCodeRepository;
import com.example.universitymanagementsystem.service.VerificationCodeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final ApplicantApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Boolean verificateApplicantApplication(VerificationCode verificationCode) throws Exception {
         VerificationCode currentVerificationCode = verificationCodeRepository
                 .getByApplicantApplicationId(verificationCode.getApplicantApplicationId())
                 .orElseThrow(() -> new ApplicantApplicationNotFoundException("There is no user with this number"));

         if (!currentVerificationCode.getCode().equals(verificationCode.getCode())) {
             throw new VerificationCodeException("Incorrect verification code has been entered");
         }

         if (LocalDateTime.now().isAfter(currentVerificationCode.getExpireDate())){
             throw new VerificationCodeException("The verification code has expired");
         }

        applicationRepository
                .findById(verificationCode.getApplicantApplicationId())
                .orElseThrow(() -> new ApplicantApplicationNotFoundException("There is no user with this number"))
                .setIsActivated(true);

         currentVerificationCode.setIsApplied(true);

         return true;
    }
}
