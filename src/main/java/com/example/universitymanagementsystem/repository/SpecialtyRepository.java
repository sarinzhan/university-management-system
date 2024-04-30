package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty,Long> {
}
