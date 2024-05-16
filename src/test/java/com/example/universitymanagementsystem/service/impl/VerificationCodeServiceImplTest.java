package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
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
public class VerificationCodeServiceImplTest {
    @InjectMocks
    private VerificationCodeServiceImpl verificationCodeService;

    @Mock
    private VerificationCodeRepository verificationCodeRepository;

    @Mock
    private ApplicantApplicationRepository applicantApplicationRepository;

    @Test
    void verificateApplicantApplication_ok(){
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setApplicantApplicationId(1L);
        verificationCode.setCode("Afr-d4h");

        VerificationCode currentVerificationCode = new VerificationCode();
        currentVerificationCode.setApplicantApplicationId(1L);
        currentVerificationCode.setCode("Afr-d4h");
        currentVerificationCode.setExpireDate(LocalDateTime.now().plusMinutes(5));

        when(verificationCodeRepository.getByApplicantApplicationId(anyLong())).thenReturn(Optional.of(currentVerificationCode));
        when(applicantApplicationRepository.findById(anyLong())).thenReturn(Optional.of(new ApplicantApplication()));

        boolean result = verificationCodeService.verificateApplicantApplication(verificationCode);
        assertTrue(result);
    }

    @Test
    void verificateApplicantApplication_incorrect_code(){
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setApplicantApplicationId(1L);
        verificationCode.setCode("Afr");

        BaseBusinessLogicException baseBusinessLogicException = assertThrows(BaseBusinessLogicException.class, () ->
                verificationCodeService.verificateApplicantApplication(verificationCode));
        assertEquals(baseBusinessLogicException.getMessage(), "Неверный код подтверждения. Должно быть 7 символов. Пример: xxx-xxx");
    }

    @Test
    void verificateApplicantApplication_invalid_code() {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setApplicantApplicationId(1L);
        verificationCode.setCode("Afr-d4h");

        VerificationCode currentVerificationCode = new VerificationCode();
        currentVerificationCode.setApplicantApplicationId(1L);
        currentVerificationCode.setCode("Afr-d4h");

        when(verificationCodeRepository.getByApplicantApplicationId(anyLong())).thenReturn(Optional.empty());

        BaseBusinessLogicException baseBusinessLogicException = assertThrows(BaseBusinessLogicException.class, () ->
                verificationCodeService.verificateApplicantApplication(verificationCode));
        assertEquals(baseBusinessLogicException.getMessage(), "Неверный код подтверждения. Повторите еще раз!");
    }

    @Test
    void verificateApplicantApplication_expired_code(){
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setApplicantApplicationId(1L);
        verificationCode.setCode("Afr-d4h");

        VerificationCode currentVerificationCode = new VerificationCode();
        currentVerificationCode.setApplicantApplicationId(1L);
        currentVerificationCode.setCode("Afr-d4h");
        currentVerificationCode.setExpireDate(LocalDateTime.now().minusMinutes(5));

        when(verificationCodeRepository.getByApplicantApplicationId(anyLong())).thenReturn(Optional.of(currentVerificationCode));

        BaseBusinessLogicException baseBusinessLogicException = assertThrows(BaseBusinessLogicException.class, () ->
                verificationCodeService.verificateApplicantApplication(verificationCode));
        assertEquals(baseBusinessLogicException.getMessage(), "Код подтверждения истек. Запросите новый");
    }

    @Test
    void verificateApplicantApplication_applicant_not_found(){
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setApplicantApplicationId(1L);
        verificationCode.setCode("Afr-d4h");

        VerificationCode currentVerificationCode = new VerificationCode();
        currentVerificationCode.setApplicantApplicationId(1L);
        currentVerificationCode.setCode("Afr-d4h");
        currentVerificationCode.setExpireDate(LocalDateTime.now().plusMinutes(5));

        when(verificationCodeRepository.getByApplicantApplicationId(anyLong())).thenReturn(Optional.of(currentVerificationCode));
        when(applicantApplicationRepository.findById(anyLong())).thenReturn(Optional.empty());

        BaseBusinessLogicException baseBusinessLogicException = assertThrows(BaseBusinessLogicException.class, () ->
                verificationCodeService.verificateApplicantApplication(verificationCode));
        assertEquals(baseBusinessLogicException.getMessage(), "Ошибка подтверждения почты. Попробуйте еще раз.");
    }
}
