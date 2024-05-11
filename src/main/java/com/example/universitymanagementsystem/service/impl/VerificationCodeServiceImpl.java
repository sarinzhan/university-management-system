package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.VerificationCodeRepository;
import com.example.universitymanagementsystem.service.VerificationCodeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final ApplicantApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Boolean verificateApplicantApplication(VerificationCode verificationCode){
         VerificationCode currentVerificationCode = verificationCodeRepository
                 .getByApplicantApplicationId(verificationCode.getApplicantApplicationId())
                 .orElseThrow(() -> new BaseBusinessLogicException("There is no user with this number"));

         if (!currentVerificationCode.getCode().equals(verificationCode.getCode())) {
             throw new BaseBusinessLogicException("Incorrect verification code has been entered");
         }

         if (LocalDateTime.now().isAfter(currentVerificationCode.getExpireDate())){
             throw new BaseBusinessLogicException("The verification code has expired");
         }

        applicationRepository
                .findById(verificationCode.getApplicantApplicationId())
                .orElseThrow(() -> new BaseBusinessLogicException("There is no user with this number"))
                .setIsActivated(true);

         currentVerificationCode.setIsApplied(true);

         return true;
    }
    @Override
    public VerificationCode generateCode(Long applicantApplicationId){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<6;i++){
            builder.append(chars.charAt(new Random().nextInt(chars.length())));
            if(i == 2){
                builder.append("-");
            }
        }
        VerificationCode verCode = new VerificationCode();
        verCode.setCode(builder.toString());
        verCode.setExpireDate(LocalDateTime.now().plusDays(1));
        verCode.setIsApplied(false);
        verCode.setApplicantApplicationId(applicantApplicationId);
        verificationCodeRepository.save(verCode);
        return verCode;

    }
}
