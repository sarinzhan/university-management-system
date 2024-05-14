package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class ApplicantApplicationServiceImplTest {
    @InjectMocks
    private ApplicantApplicationServiceImpl applicantApplicationService;

    @Spy
    private ApplicantApplicationRepository applicantApplicationRepository;

    @Test
    void getEmailVerifiedApplicants_OK() {
        ApplicantApplication applicantApplication = new ApplicantApplication();
        applicantApplication.setEmail("test@gmail.com");
        when (applicantApplicationRepository.getAllNonCheckedActivated()).thenReturn(List.of(applicantApplication));
        List<ApplicantApplication> emailVerifiedApplicants = applicantApplicationService.getEmailVerifiedApplicants();
        Assertions.assertEquals(emailVerifiedApplicants.size(),1);
        Assertions.assertEquals(emailVerifiedApplicants.get(0),applicantApplication);
    }

    @Test
    void getEmailVerifiedApplicants_nothing() {
        when (applicantApplicationRepository.getAllNonCheckedActivated()).thenReturn(List.of());
        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> applicantApplicationService.getEmailVerifiedApplicants());
        Assertions.assertEquals(exception.getMessage(),"Нету заявок абитуриентов на проверку");
    }
}