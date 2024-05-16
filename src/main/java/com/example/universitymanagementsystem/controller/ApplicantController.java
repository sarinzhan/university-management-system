package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.ApplicantApplicationVerifyRequestDto;
import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.dto.response.ApplicantApplicationVerifyResponseDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.service.ApplicantVerificationFacade;
import com.example.universitymanagementsystem.mapper.ApplicantApplicationVerifyResponseMapper;
import com.example.universitymanagementsystem.mapper.RegisterApplicantApplicationMapper;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Applicant", description = "APIs for applicants")
@RestController
@RequestMapping("/applicant")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantApplicationService applicantApplicationService;

    private final RegisterApplicantApplicationMapper registerApplicantApplicationMapper;
    private final ApplicantVerificationFacade applicantVerificationFacade;
    private final ApplicantApplicationVerifyResponseMapper applicantApplicationVerifyResponseMapper;

    @Operation(summary = "Register applicant",description = "Register applicant application without activating.The response is applicant id.")
    @PostMapping("/register-applicant")
    public CommonResponseDto<Long> registerApplicant(@RequestBody RegisterApplicantApplicationDto requestDto) {
        Long id = applicantApplicationService.registerApplicantApplication(
                registerApplicantApplicationMapper.dtoToEntity(requestDto));

        return new CommonResponseDto<Long>()
                .setData(id)
                .setOk();
    }

    @PostMapping(value = "/verify")
    @PreAuthorize("hasRole('ADMISSION_COMMISSION')")
    public CommonResponseDto<Void> verifyApplicantData(
            @RequestBody ApplicantApplicationVerifyRequestDto applicantApplicationVerifyRequestDto
    ) {
        this.applicantVerificationFacade.verifyApplicantApplication(
                applicantApplicationVerifyRequestDto.getApplicantApplicationId(),
                applicantApplicationVerifyRequestDto.getMessage(),
                applicantApplicationVerifyRequestDto.isVerify()
        );
        return new CommonResponseDto<Void>().setOk();
    }
    @Operation(description = "Applicant applications to verify if applicant fill data correctly")
    @GetMapping("/get-all-to-verify")
    @PreAuthorize("hasRole('ADMISSION_COMMISSION')")
    public CommonResponseDto<List<ApplicantApplicationVerifyResponseDto>> getToVerify(){
        return new CommonResponseDto<List<ApplicantApplicationVerifyResponseDto>>()
                .setOk()
                .setData(
                        applicantApplicationVerifyResponseMapper
                                .listEntityToDto(applicantApplicationService.getEmailVerifiedApplicants())
                );
    }
}
