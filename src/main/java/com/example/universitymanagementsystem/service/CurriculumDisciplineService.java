package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.study.CurriculumDiscipline;

import java.util.List;

public interface CurriculumDisciplineService {
    CurriculumDiscipline create(CurriculumDiscipline curriculumDiscipline);

    List<CurriculumDiscipline> getAllByCurriculumId(Long curriculumId);
}
