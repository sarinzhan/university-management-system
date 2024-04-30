package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpecialtyAdmissionRepository extends JpaRepository<SpecialtyAdmission,Long> {
//    @Query(value = "select s from SpecialtyAdmission s " +
//            "where s.startDate < CURRENT_DATE and CURRENT_DATE > s.endDate and s.specialty.id")
    Optional<SpecialtyAdmission> getActiveBySpecialtyId(Long specId);
}
