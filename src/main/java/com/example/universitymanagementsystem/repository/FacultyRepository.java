package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.uni_struct.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
