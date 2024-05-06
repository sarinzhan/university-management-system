package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.CandidateRepository;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.spi.LoggingEventBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantApplicationServiceImpl implements ApplicantApplicationService {
    private final CandidateRepository candidateRepository;
    private final ApplicantApplicationRepository applicantApplicationRepository;
    private final SpecialtyAdmissionRepository specialtyAdmissionRepository;

    @Override
    public Long registerApplicantApplication(ApplicantApplication app) throws
            ApplicantApplicationAlreadyAppliedException,
            SpecialtyAdmissionInvalidException
    {
        if(applicantApplicationRepository.findByPnWhichIsNonChecked(app.getPersonalNumber())
                .isPresent()){
            throw new ApplicantApplicationAlreadyAppliedException("Абитуриент уже ожидает проверки данных");
        }

        candidateRepository.findActiveByPn(app.getPersonalNumber())
                .orElseThrow(() -> new ApplicantApplicationAlreadyAppliedException("Абитуриент уже числится кандидатом"));

                specialtyAdmissionRepository
                .getActiveBySpecId(app.getSpecialty().getId())
                .orElseThrow(() -> new SpecialtyAdmissionInvalidException("Набор по выбранному направлению не активен"));

//        LoggingEventBuilder loggingEventBuilder = this.log.atError();

        return applicantApplicationRepository.save(app).getId();

    }
}
