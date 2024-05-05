package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.CandidateRepository;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import org.springframework.stereotype.Service;

@Service
public class ApplicantApplicationServiceImpl implements ApplicantApplicationService {
    private final CandidateRepository candidateRepository;
    private final ApplicantApplicationRepository applicantApplicationRepository;
    private final SpecialtyAdmissionRepository specialtyAdmissionRepository;

    public ApplicantApplicationServiceImpl(
            CandidateRepository candidateRepository, ApplicantApplicationRepository appRep,
            SpecialtyAdmissionRepository specialtyAdmissionRepository) {
        this.candidateRepository = candidateRepository;
        this.applicantApplicationRepository = appRep;
        this.specialtyAdmissionRepository = specialtyAdmissionRepository;
    }

    @Override
    public Long registerApplicantApplication(ApplicantApplication app) throws
            ApplicantApplicationAlreadyAppliedException,
            SpecialtyAdmissionInvalidException
    {
        specialtyAdmissionRepository
                .getActiveBySpecId(app.getSpecialty().getId())
                .orElseThrow(() -> new SpecialtyAdmissionInvalidException("Набор по выбранному направлению не активен"));
        if(applicantApplicationRepository.findByPnWhichIsNonChecked(app.getPersonalNumber())
                .isPresent()){
            throw new ApplicantApplicationAlreadyAppliedException("Абитуриент уже ожидает проверки данных");
        }

        candidateRepository.findActiveByPn(app.getPersonalNumber())
                .orElseThrow(() -> new ApplicantApplicationAlreadyAppliedException("Абитуриент уже числится кандидатом"));

        return applicantApplicationRepository.save(app).getId();

    }
}
