package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.study.CurriculumDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurriculumDisciplineRepository extends JpaRepository<CurriculumDiscipline,Long> {\

    @Query(value = "select c from curriculum_discipline c " +
            "where c.curriculum.id = :curriculumId")
    List<CurriculumDiscipline> getAllByCurriculumId(Long curriculumId);
}
