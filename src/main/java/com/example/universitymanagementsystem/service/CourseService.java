package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.study.Course;

import java.util.List;

public interface CourseService {
    List<Course> addAll(Iterable<Course> iterableCourse);
}
