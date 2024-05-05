package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.exception.ApplicantApplicationAlreadyAppliedException;
import com.example.universitymanagementsystem.exception.SpecialtyAdmissionInvalidException;
import com.example.universitymanagementsystem.mapper.RegisterApplicantApplicationMapper;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Applicant", description = "For applicants api")
@RestController
@RequestMapping("/applicant")
public class ApplicantController {
    private final ApplicantApplicationService applicantApplicationService;
    private final RegisterApplicantApplicationMapper registerApplicantApplicationMapper;

    public ApplicantController(ApplicantApplicationService applicantApplicationService, RegisterApplicantApplicationMapper registerApplicantApplicationMapper) {
        this.applicantApplicationService = applicantApplicationService;
        this.registerApplicantApplicationMapper = registerApplicantApplicationMapper;
    }

    @PostMapping("/register-applicant")
    public CommonResponseDto<Long> registerApplicant(@RequestBody(required = false) RegisterApplicantApplicationDto requestDto) {
        CommonResponseDto<Long> responseDto = new CommonResponseDto<>();
        try {
            Long id = applicantApplicationService.registerApplicantApplication(
                    registerApplicantApplicationMapper.dtoToEntity(requestDto));
            responseDto.setData(id);
            responseDto.setStatus(201);
            responseDto.setMessage("Created");
        } catch (ApplicantApplicationAlreadyAppliedException | SpecialtyAdmissionInvalidException ex) {
            responseDto.setStatus(400);
            responseDto.setMessage(ex.getMessage());
            return responseDto;
        }catch (Exception ex){
            responseDto.setStatus(500);
            responseDto.setMessage("Internal server error");
        }
        return responseDto;
    }
}
