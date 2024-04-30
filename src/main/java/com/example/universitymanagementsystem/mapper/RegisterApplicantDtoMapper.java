package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplicationStatus;
import com.example.universitymanagementsystem.exception.SpecialtyNotFoundException;
import com.example.universitymanagementsystem.repository.DepartmentRepository;
import com.example.universitymanagementsystem.repository.FacultyRepository;
import com.example.universitymanagementsystem.repository.SpecialtyRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
public class RegisterApplicantDtoMapper {



    public static ApplicantApplication dtoToEntity(RegisterApplicantApplicationDto dto) throws Exception{
//        return new ApplicantApplication(
//                ApplicantApplicationStatus.CREATED,
//                LocalDateTime.now(),
//                dto.getTestScore(),
//                specialtyRepository.findById(dto.getSpecialtyId())
//                        .orElseThrow(() -> new SpecialtyNotFoundException("Specialty by id " + dto.getSpecialtyId() + " not found")),
//                departmentRepository.findById(dto.getDepartmentId()),
//
//        )
        return new ApplicantApplication();
    }
}
