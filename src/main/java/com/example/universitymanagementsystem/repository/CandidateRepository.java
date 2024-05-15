package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.applyment.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    @Query("SELECT c FROM candidate c" +
            " where c.specialtyAdmission.id = :admissionId")
    Optional<List<Candidate>> findAllByAdmissionId(Long admissionId);

    @Query("SELECT c FROM candidate where c.specialtyAdmission")
    Optional<Candidate> findActiveByPn(Long personalNumber);
}
