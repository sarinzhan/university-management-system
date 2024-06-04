package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.study.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {
}
