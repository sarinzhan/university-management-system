package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpecialtyAdmissionRepository extends JpaRepository<SpecialtyAdmission,Long> {
    @Query(value = "select s from specialty_admission s " +
            "where s.startDate < CURRENT_DATE and CURRENT_DATE > s.endDate and s.specialty.id = :specialtyId")
    Optional<SpecialtyAdmission> getActiveBySpecId(Long specialtyId);

    @Query(value = "select s from specialty_admission s " +
            "where s.startDate < CURRENT_DATE and CURRENT_DATE > s.endDate")
    List<SpecialtyAdmission> getAllActiveBySpecId();

}
