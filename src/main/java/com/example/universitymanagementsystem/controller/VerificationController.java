package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.ApplicantVerificationCodeDto;
import com.example.universitymanagementsystem.mapper.ApplicantVerificationCodeMapper;
import com.example.universitymanagementsystem.mapper.RegisterApplicantApplicationMapper;
import com.example.universitymanagementsystem.service.VerificationCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Verification API")
@RestController
@RequestMapping("/verification")
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationCodeService verificationCodeService;
    private final ApplicantVerificationCodeMapper applicantApplicationMapper;

    @PostMapping("/activate-applicant")
    public String activate(@RequestBody ApplicantVerificationCodeDto verificationCodeDto){
        try{
            verificationCodeService.verificateApplicantApplication(applicantApplicationMapper.dtoToEntity(verificationCodeDto));
            return "Ok";
        } catch (Exception ex){
            return "Error";
        }
    }
}
