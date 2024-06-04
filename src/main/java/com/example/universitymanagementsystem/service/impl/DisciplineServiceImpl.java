package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.study.Discipline;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.DisciplineRepository;
import com.example.universitymanagementsystem.service.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;

    @Override
    public List<Discipline> getAll() {
        List<Discipline> disciplineList =  disciplineRepository.findAll();
        if(disciplineList.isEmpty()){
            throw new BaseBusinessLogicException("Не удалось найти дисциплины");
        }
        return disciplineList;
    }
}
