package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.exception.CandidateNotFoundException;
import com.example.universitymanagementsystem.repository.CandidateRepository;
import com.example.universitymanagementsystem.service.CandidateService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<Candidate> getAllActiveBySpecId(Long specialtyId) throws CandidateNotFoundException {
        List<Candidate> candidateList = candidateRepository.findAll().stream()
                .filter(x -> x.getSpecialtyAdmission().getStartDate().isAfter(LocalDateTime.now()))
                .filter(x -> x.getSpecialtyAdmission().getEndDate().isBefore(LocalDateTime.now()))
                .filter(x -> x.getSpecialtyAdmission().getSpecialty().getId().equals(specialtyId))
                .toList();
        if(candidateList.isEmpty()){
            throw new CandidateNotFoundException("Кандидатов по данному направлению отсутствуют");
        }
        return candidateList;
    }
}
