package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantApplicationRepository extends JpaRepository<ApplicantApplication,Long> {
    Optional<ApplicantApplication> findByPersonalNumber(Long pn);
}
