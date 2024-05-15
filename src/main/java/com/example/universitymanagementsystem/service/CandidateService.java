package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.applyment.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> getActive(Long admissionId);



    Long addCandidate(Candidate candidate);
}
