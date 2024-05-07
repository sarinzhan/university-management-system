package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.applyment.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    @Query("select c from Candidate c where c.applicantApplication.personalNumber = :pn")
    Optional<Candidate> findByPn(Long pn);

    @Query("select c from Candidate c " +
            "where c.applicantApplication.personalNumber = :pn " +
            "and c.specialtyAdmission.startDate < CURRENT_DATE " +
            "and c.specialtyAdmission.endDate > CURRENT_DATE ")
    Optional<Candidate> findActiveByPn(Long pn);

    @Query("select c from Candidate c " +
            "where c.specialtyAdmission.startDate <CURRENT_DATE " +
            "and c.specialtyAdmission.endDate > CURRENT_DATE")
    List<Candidate> findAllActive();
}
