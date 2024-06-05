package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.uni_struct.Specialty;

import java.util.List;

public interface SpecialtyService {
    List<Specialty> getAll();
    Specialty getById(Long id);

    List<Specialty> getAllByFacultyId(Long facultyId);
}
