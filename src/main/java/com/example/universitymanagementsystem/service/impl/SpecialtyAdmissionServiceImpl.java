package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import com.example.universitymanagementsystem.service.SpecialtyService;
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

    private final SpecialtyAdmissionRepository admissionRepository;

    private final SpecialtyService specialtyService;

    @Override
    public List<SpecialtyAdmission> getActiveAdmissions() {
        List<SpecialtyAdmission> allActive = admissionRepository.getAllActive();
        if (allActive.isEmpty()) {
            throw new BaseBusinessLogicException("Наборы не объявлены");
        } else {
            return allActive;
        }
    }

    @Override
    public List<SpecialtyAdmission> getActiveAdmissions(Long facultyId) {
        List<SpecialtyAdmission> allActiveBySpecId = admissionRepository.getAllActive(facultyId);
        if (allActiveBySpecId.isEmpty()) {
            throw new BaseBusinessLogicException("Набор не объявлен");
        }

        return allActiveBySpecId;
    }

    @Override

    public List<SpecialtyAdmission> getAllAdmissions() {
        List<SpecialtyAdmission> allAdmissions = admissionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(SpecialtyAdmission::getStartDate))
                .peek(admission -> {
                    LocalDateTime currentDate = LocalDateTime.now();
                    admission.setIsActive(currentDate.isAfter(admission.getStartDate())
                            && currentDate.isBefore(admission.getEndDate()));
                })
                .toList();

        if (allAdmissions.isEmpty()) {
            throw new BaseBusinessLogicException("Наборов не объявлялось");
        }

        return allAdmissions;
    }

    @Override
    public SpecialtyAdmission getAdmissionById(Long admissionId) {
        return admissionRepository.getByAdmissionId(admissionId)
                .orElseThrow(() -> new BaseBusinessLogicException("Набора не найден"));
    }


    @Override
    public Long create(SpecialtyAdmission admission) {
        Specialty specialty = specialtyService.getById(admission.getSpecialty().getId());
        admission.setDepartment(specialty.getDepartment());
        admission.setFaculty(specialty.getDepartment().getFaculty());

        Boolean collision = admissionRepository.isCollision(admission.getStartDate(), admission.getEndDate(), admission.getSpecialty().getId());
        if (collision) {
            throw new BaseBusinessLogicException("В указанный период времени уже есть набор");
        }
        try {
            return admissionRepository.save(admission).getId();
        } catch (Exception ex) {
            throw new BaseBusinessLogicException("Не удалось создать набор");
        }
    }
    public SpecialtyAdmission getById(Long admissionId){
        return admissionRepository.findById(admissionId)
                .orElseThrow(() -> new BaseBusinessLogicException("Набор не найден"));
    }

    @Override
    public List<SpecialtyAdmission> getAllPlanned() {
        List<SpecialtyAdmission> specialtyAdmissionList =  admissionRepository.getAllPlanned(LocalDateTime.now());
        if(specialtyAdmissionList.isEmpty()){
            throw new BaseBusinessLogicException("Запланированные наборы отсутствуют");
        }
        return specialtyAdmissionList;
    }

    @Override
    public List<SpecialtyAdmission> getActiveAndNonDistributed() {
        List<SpecialtyAdmission> allActive = admissionRepository.getActiveAndNonDistributed();
        if (allActive.isEmpty()) {
            throw new BaseBusinessLogicException("Наборы не объявлены");
        } else {
            return allActive;
        }
    }

}
