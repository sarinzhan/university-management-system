package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.uni_struct.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
