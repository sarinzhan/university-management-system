package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.study.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline,Long> {
}
