package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.study.Semester;

import java.util.List;

public interface SemesterService {
    List<Semester> getAllByGroupId(Long groupId);
}
