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
            "where c.applicantApplication.personalNumber = :personalNumber " +
            "and local_datetime  between c.specialtyAdmission.startDate and c.specialtyAdmission.endDate")
    Optional<Candidate> findActiveByPn(Long personalNumber);

    @Query("select c from Candidate c " +
            "where c.specialtyAdmission.id = :specialtyAdmissionId " +
            "and local_datetime between c.specialtyAdmission.startDate and c.specialtyAdmission.endDate")
    List<Candidate> findAllActiveBySpecId(Long specialtyAdmissionId);

    @Query("select c from Candidate c " +
            "where local_datetime  between c.specialtyAdmission.startDate and c.specialtyAdmission.endDate")
    List<Candidate> findAllActive();
}
