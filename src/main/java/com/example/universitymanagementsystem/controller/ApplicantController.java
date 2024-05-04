package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.dto.response.ResponseDto;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.mapper.RegisterApplicantApplicationMapper;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Applicant", description = "For applicants api")
@RestController
@RequestMapping("/applicant")
public class ApplicantController {
    private final ApplicantApplicationService applicantApplicationService;

    public ApplicantController(ApplicantApplicationService applicantApplicationService) {
        this.applicantApplicationService = applicantApplicationService;
    }

    @PostMapping("/register-applicant")
    public ResponseDto registerApplicant(@RequestBody RegisterApplicantApplicationDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setData( applicantApplicationService
                    .registerApplicantApplication(RegisterApplicantApplicationMapper.instance.dtoToEntity(requestDto)));
            responseDto.setStatus("OK");
        } catch (Exception ex) {
            responseDto.setStatus("ERROR");
            responseDto.setMessage(ex.getMessage());
            return responseDto;
        }
        return responseDto;
    }
}
