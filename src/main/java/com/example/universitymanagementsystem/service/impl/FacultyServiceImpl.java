package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.uni_struct.Faculty;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.FacultyRepository;
import com.example.universitymanagementsystem.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAllFaculties(){
        List<Faculty> faculties = facultyRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Faculty::getName))
                .toList();

        if (faculties.isEmpty())
            throw new BaseBusinessLogicException("Факультеты не найдены");

        return faculties;
    }
}
