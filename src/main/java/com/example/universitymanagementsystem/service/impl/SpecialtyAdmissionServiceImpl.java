package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialtyAdmissionServiceImpl implements SpecialtyAdmissionService {

    private final SpecialtyAdmissionRepository specialtyAdmissionRepository;

    @Override
    public List<SpecialtyAdmission> getActiveAdmissions(){
        List<SpecialtyAdmission> allActive = specialtyAdmissionRepository.getAllActive();
        if(allActive.isEmpty()){
            throw new BaseBusinessLogicException("Наборы не объявлены");
        }else {
            return allActive;
        }
    }

    @Override
    public List<SpecialtyAdmission> getActiveAdmissions(Long facultyId){
        List<SpecialtyAdmission> allActiveBySpecId = specialtyAdmissionRepository.getAllActive(facultyId);
        if(allActiveBySpecId.isEmpty()){
            throw new BaseBusinessLogicException("Набор не объявлен");
        }

        return allActiveBySpecId;
    }

    @Override
    public List<SpecialtyAdmission> getAllAdmissions(){
        List<SpecialtyAdmission> allAdmissions = specialtyAdmissionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(SpecialtyAdmission::getStartDate))
                .peek(admission -> {
                    LocalDateTime currentDate = LocalDateTime.now();
                    admission.setIsActive(currentDate.isAfter(admission.getStartDate())
                            && currentDate.isBefore(admission.getEndDate()));
                })
                .toList();

        if (allAdmissions.isEmpty()){
            throw new BaseBusinessLogicException("Наборов не объявлялось");
        }

        return allAdmissions;
    }

    @Override
    public SpecialtyAdmission getAdmissionById(Long admissionId) {
        return specialtyAdmissionRepository.getByAdmissionId(admissionId)
                .orElseThrow(() -> new BaseBusinessLogicException("Набора с таким id не объявлялось"));
    }
}
