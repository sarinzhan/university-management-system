package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.PersonData;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.CandidateRepository;
import com.example.universitymanagementsystem.repository.PersonRepository;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import com.example.universitymanagementsystem.service.CandidateService;
import com.example.universitymanagementsystem.service.EmailService;
import com.example.universitymanagementsystem.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class ApplicantApplicationServiceImplTest {
    @InjectMocks
    private ApplicantApplicationServiceImpl applicantApplicationService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private ApplicantApplicationRepository applicantApplicationRepository;

    @Mock
    private SpecialtyAdmissionRepository specialtyAdmissionRepository;

    @Mock
    private VerificationCodeService verificationCodeService;

    @Mock
    private EmailService emailService;

    @Test
    void registerApplicantApplication_OK() {
        Mockito.when(applicantApplicationRepository.findByPnWhichIsNonChecked(anyLong())).thenReturn(Optional.empty());
        Mockito.when(candidateRepository.findActiveByPn(anyLong())).thenReturn(Optional.empty());
        Mockito.when(specialtyAdmissionRepository.getActiveBySpecId(anyLong())).thenReturn(Optional.of(new SpecialtyAdmission()));

        ApplicantApplication saveMock = new ApplicantApplication();
        saveMock.setId(1L);
        Mockito.when(applicantApplicationRepository.save(any(ApplicantApplication.class))).thenReturn(saveMock);

        VerificationCode verificationCode = new VerificationCode();
        Mockito.when(verificationCodeService.generateCode(anyLong())).thenReturn(verificationCode);

        ApplicantApplication genTextApp = new ApplicantApplication();
        genTextApp.setFirstName("Sarinzhan");
        Specialty specialty = new Specialty();
        specialty.setName("Программная инженерия");
        genTextApp.setSpecialty(specialty);
        String code = "rdf-1da";
//        Mockito.doReturn(" ").when(applicantApplicationService.generateText(code,genTextApp)).equals(" ");

        ApplicantApplication applicantApplication2 = new ApplicantApplication();
        applicantApplication2.setPersonalNumber(1L);
        Specialty specialty2 = new Specialty();
        specialty2.setId(1L);
        applicantApplication2.setEmail("test@example.com");
        applicantApplication2.setSpecialty(specialty2);
        Long result = applicantApplicationService.registerApplicantApplication(applicantApplication2);
        Assertions.assertNotNull(result);

//        verify(emailService).sendMessage(anyString(), anyString(), anyString());
    }
}