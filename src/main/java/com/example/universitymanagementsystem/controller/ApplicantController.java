package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.dto.response.ResponseDto;
import com.example.universitymanagementsystem.mapper.RegisterApplicantDtoMapper;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applicant")
public class ApplicantController {
    private final ApplicantApplicationService applicantApplicationService;

    public ApplicantController(ApplicantApplicationService applicantApplicationService) {
        this.applicantApplicationService = applicantApplicationService;
    }

    @PostMapping("/register-applicant")
    public Long registerApplicant(RegisterApplicantApplicationDto queryDto){
        ResponseDto responseDto = new ResponseDto();
        try{

        }catch (Exception ex){

        }
        return 1l;

    }
}
