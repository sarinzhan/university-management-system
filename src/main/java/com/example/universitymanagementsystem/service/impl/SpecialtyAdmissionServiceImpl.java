package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.exception.SpecialtyAdmissionNotFoundException;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyAdmissionServiceImpl implements SpecialtyAdmissionService {
    private final SpecialtyAdmissionRepository specialtyAdmissionRepository;


    @Override
    public List<SpecialtyAdmission> getActiveAdmissions() throws SpecialtyAdmissionNotFoundException {
        List<SpecialtyAdmission> allActiveBySpecId = specialtyAdmissionRepository.getAllActiveBySpecId();
        if(allActiveBySpecId.isEmpty()){
            throw new SpecialtyAdmissionNotFoundException("Наборы не объявлены");
        }else {
            return allActiveBySpecId;
        }
    }
}
