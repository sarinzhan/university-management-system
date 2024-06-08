package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.study.Curriculum;

import java.util.List;

public interface CurriculumService {
    Curriculum create(Curriculum curriculum);
    Curriculum getById(Long curriculumId);

    List<Curriculum> getAllBySpecialtyId(Long specialtyId,Integer semesterNumber);
}
