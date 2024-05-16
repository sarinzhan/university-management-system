package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpecialtyAdmissionRepository extends JpaRepository<SpecialtyAdmission,Long> {
    @Query(value = "select s from specialty_admission s " +
            "where s.startDate < local_datetime and local_datetime < s.endDate " +
            "and s.specialty.id = :specialtyId")
    Optional<SpecialtyAdmission> getActiveBySpecId(Long specialtyId);

    @Query(value = "select s from specialty_admission s " +
            "where local_datetime between s.startDate and s.endDate")
    List<SpecialtyAdmission> getAllActiveBySpecId();

}
