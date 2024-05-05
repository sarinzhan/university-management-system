package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.exception.*;

public interface ApplicantApplicationService {
    Long registerApplicantApplication(ApplicantApplication app) throws ApplicantApplicationAlreadyAppliedException, SpecialtyAdmissionInvalidException;
}
