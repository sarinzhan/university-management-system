package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.exception.*;
import org.springframework.core.annotation.Order;

public interface ApplicantApplicationService {
    Long registerApplicantApplication(ApplicantApplication app) throws ApplicantApplicationAlreadyExistException, InvalidVerificationCodeExpcetion, VerificationCodeExpiredException, ApplicantApplicationAlreadyAppliedException, SpecialtyAdmissionInvalidException;
}
