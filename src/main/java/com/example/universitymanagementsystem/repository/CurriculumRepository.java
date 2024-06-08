package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.study.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {

    @Query(value = "select c from curriculum c " +
            "where c.specialty.id = :specialtyId " +
            "and c.semesterNumber = :semesterNumber")
    List<Curriculum> getAllBySpecialtyId(Long specialtyId,Integer semesterNumber);
}
