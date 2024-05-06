package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.exception.CandidateNotFoundException;

import java.util.List;

public interface CandidateService {
    List<Candidate> getAllActiveBySpecId(Long specialtyId) throws CandidateNotFoundException;
}
