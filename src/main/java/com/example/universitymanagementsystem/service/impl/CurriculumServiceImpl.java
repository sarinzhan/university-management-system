package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.study.Curriculum;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.CurriculumRepository;
import com.example.universitymanagementsystem.service.CurriculumService;
import com.example.universitymanagementsystem.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final SpecialtyService specialtyService;

    @Override
    public Curriculum create(Curriculum curriculum) {
        specialtyService.getById(curriculum.getSpecialty().getId());
        try{
            return curriculumRepository.save(curriculum);
        }catch (Exception ex){
            throw new BaseBusinessLogicException("Не удалось сохранить учебный план %s".formatted(curriculum.getIdentifierName()));
        }
    }
    @Override
    public Curriculum getById(Long curriculumId) {
        return curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new BaseBusinessLogicException("Не удалось найти учебный план"));
    }
}
