package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.CandidateRepository;
import com.example.universitymanagementsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    @Override
    public List<Candidate> getAllActiveBySpecId(Long specialtyId) {
        List<Candidate> candidateList = candidateRepository.findAllActiveBySpecId(specialtyId);
        if(candidateList.isEmpty()){
            throw new BaseBusinessLogicException("Кандидатов по данному направлению отсутствуют");
        }
        return candidateList;
    }

    @Override
    public void addCandidate(Candidate candidate) {
        this.candidateRepository.save(candidate);
    }
}

