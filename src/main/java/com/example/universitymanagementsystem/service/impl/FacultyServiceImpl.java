package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.uni_struct.Faculty;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.FacultyRepository;
import com.example.universitymanagementsystem.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    @Override
    public Faculty getById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new BaseBusinessLogicException("Не удалось найти факультет"));
    }
}
