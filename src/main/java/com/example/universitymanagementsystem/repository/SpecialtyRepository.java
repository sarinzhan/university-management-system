package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty,Long> {

    @Query(value ="select s from specialty  s " +
            "where s.department.faculty.id = :facultyId")
    List<Specialty> getAllByFacultyId(Long facultyId);
}
