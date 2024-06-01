package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import com.example.universitymanagementsystem.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyAdmissionServiceImpl implements SpecialtyAdmissionService {

    private final SpecialtyAdmissionRepository admissionRepository;

    private final SpecialtyService specialtyService;

    @Override
    public List<SpecialtyAdmission> getActiveAdmissions(){
        List<SpecialtyAdmission> allActive = admissionRepository.getAllActive();
        if(allActive.isEmpty()){
            throw new BaseBusinessLogicException("Наборы не объявлены");
        }else {
            return allActive;
        }
    }

    @Override
    public List<SpecialtyAdmission> getActiveAdmissions(Long facultyId) {
        List<SpecialtyAdmission> allActiveBySpecId = admissionRepository.getAllActive(facultyId);
        if(allActiveBySpecId.isEmpty()){
            throw new BaseBusinessLogicException("Набор не объявлены");
        }
        return allActiveBySpecId;
    }

    @Override
    public Long create(SpecialtyAdmission admission) {
        Specialty specialty = specialtyService.getById(admission.getSpecialty().getId());
        admission.setDepartment(specialty.getDepartment());
        admission.setFaculty(specialty.getDepartment().getFaculty());

        Boolean collision = admissionRepository.isCollision(admission.getStartDate(), admission.getEndDate(),admission.getSpecialty().getId());
        if(collision){
            throw new BaseBusinessLogicException("В указанный период времени уже есть набор");
        }
        try{
            return admissionRepository.save(admission).getId();
        }catch (Exception ex){
            throw  new BaseBusinessLogicException("Не удалось создать набор");
        }
    }
}
