package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicantApplicationRepository extends JpaRepository<ApplicantApplication,Long> {
    Optional<ApplicantApplication> findByPersonalNumber(Long pn);

    @Query("select a from applicant_application a where a.isChecked = true")
    Optional<ApplicantApplication> findByPnWhichIsChecked(Long personalNumber);

    @Query("select a from applicant_application a where a.isChecked = false")
    Optional<ApplicantApplication> findByPnWhichIsNonChecked(Long personalNumber);
    @Query("select a from applicant_application a " +
            "where a.isChecked = false and a.isActivated = true")
    List<ApplicantApplication> getAllNonCheckedActivated();
}
