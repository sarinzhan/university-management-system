package com.example.universitymanagementsystem.constraint;

import com.example.universitymanagementsystem.dto.request.CreateAdmissionRequestDto;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdmissionStartBeforeEndConstraintValidator implements ConstraintValidator<AdmissionStartBeforeEndConstraint, CreateAdmissionRequestDto> {
    @Override
    public boolean isValid(CreateAdmissionRequestDto value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value.getStartDate() == null || value.getEndDate() == null) {
            return true;
        }
        return value.getStartDate().isBefore(value.getEndDate());
    }
}
