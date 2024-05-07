package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.exception.*;

import javax.mail.MessagingException;

public interface ApplicantApplicationService {
    Long registerApplicantApplication(ApplicantApplication app) throws
            ApplicantApplicationAlreadyAppliedException,
            SpecialtyAdmissionInvalidException,
            MessagingException;
}
