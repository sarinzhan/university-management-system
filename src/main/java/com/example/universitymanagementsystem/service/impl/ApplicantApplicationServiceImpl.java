package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.CandidateRepository;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import com.example.universitymanagementsystem.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantApplicationServiceImpl implements ApplicantApplicationService {
    private final CandidateRepository candidateRepository;
    private final ApplicantApplicationRepository applicantApplicationRepository;
    private final SpecialtyAdmissionRepository specialtyAdmissionRepository;
    private final EmailService emailService;

    @Override
    public Long registerApplicantApplication(ApplicantApplication app) throws
            ApplicantApplicationAlreadyAppliedException,
            SpecialtyAdmissionInvalidException,
            MessagingException {
        if(applicantApplicationRepository.findByPnWhichIsNonChecked(app.getPersonalNumber())
                .isPresent()){
            throw new ApplicantApplicationAlreadyAppliedException("Абитуриент уже ожидает проверки данных");
        }

        candidateRepository.findActiveByPn(app.getPersonalNumber())
                .orElseThrow(() -> new ApplicantApplicationAlreadyAppliedException("Абитуриент уже числится кандидатом"));

                specialtyAdmissionRepository
                .getActiveBySpecId(app.getSpecialty().getId())
                .orElseThrow(() -> new SpecialtyAdmissionInvalidException("Набор по выбранному направлению не активен"));

        emailService.sendMessage(app.getEmail(), "Verification", generateCode());
        return applicantApplicationRepository.save(app).getId();
    }

    private String generateCode(){
        return Integer.toString(new Random().nextInt(100000, 999999));
    }
}
