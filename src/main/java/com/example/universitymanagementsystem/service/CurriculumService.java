package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.study.Curriculum;

public interface CurriculumService {
    Curriculum create(Curriculum curriculum);
    Curriculum getById(Long curriculumId);
}
