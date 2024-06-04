package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.study.CurriculumDiscipline;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.CurriculumDisciplineRepository;
import com.example.universitymanagementsystem.service.CurriculumDisciplineService;
import com.example.universitymanagementsystem.service.CurriculumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurriculumDisciplineServiceImpl implements CurriculumDisciplineService {

    private final CurriculumService curriculumService;

    private final CurriculumDisciplineRepository curriculumDisciplineRepository;

    @Override
    public CurriculumDiscipline create(CurriculumDiscipline curriculumDiscipline) {
        curriculumService.getById(curriculumDiscipline.getCurriculum().getId());
        try {
            return curriculumDisciplineRepository.save(curriculumDiscipline);
        }catch (Exception e){
            throw new BaseBusinessLogicException("Не удалось добавить дисциплину к учебному плану (%s)".formatted(curriculumDiscipline.getCurriculum().getIdentifierName()));
        }
    }
}