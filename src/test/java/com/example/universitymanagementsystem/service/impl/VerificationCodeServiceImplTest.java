package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class VerificationCodeServiceImplTest {
    @InjectMocks
    private VerificationCodeServiceImpl verificationCodeService;
    @Mock
    private VerificationCodeRepository verificationCodeRepository;
    @Mock
    private ApplicantApplicationRepository applicationRepository;

    @Test
    void verificateApplicantApplication_success() {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode("Dda-42d");
        VerificationCode currenVerCode = new VerificationCode();
        currenVerCode.setCode("Dda-42d");
        currenVerCode.setExpireDate(LocalDateTime.now().plusMinutes(5));
        when(verificationCodeRepository.getByApplicantApplicationId(1L)).thenReturn(Optional.of(currenVerCode));

        verificationCode.setApplicantApplicationId(1L);
        when(applicationRepository.findById(anyLong())).thenReturn(Optional.of(new ApplicantApplication()));

        Boolean result = verificationCodeService.verificateApplicantApplication(verificationCode);
        assertEquals(true,result);
    }
}