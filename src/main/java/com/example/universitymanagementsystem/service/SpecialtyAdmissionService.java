package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.exception.SpecialtyAdmissionNotFoundException;

import java.util.List;

public interface SpecialtyAdmissionService {
    List<SpecialtyAdmission> getActiveAdmissions() throws SpecialtyAdmissionNotFoundException;
}
