package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.SpecialtyRepository;
import com.example.universitymanagementsystem.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialties = specialtyRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Specialty::getName))
                .toList();

        if (specialties.isEmpty())
            throw new BaseBusinessLogicException("Специальности не найдены");

        return specialties;
    }
    @Override
    public Specialty getById(Long id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new BaseBusinessLogicException("Не удалось найти специальность"));
    }
}
