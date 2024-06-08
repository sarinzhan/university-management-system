package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.study.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
