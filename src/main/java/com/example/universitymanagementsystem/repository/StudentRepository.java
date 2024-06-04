package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.uni_struct.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
