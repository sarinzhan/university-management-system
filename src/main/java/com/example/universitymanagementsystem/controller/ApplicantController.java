package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.dto.response.ApplicantApplicationVerifyResponseDto;
import com.example.universitymanagementsystem.dto.response.CandidatesInfoResponseDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.entity.uni_struct.Faculty;
import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import com.example.universitymanagementsystem.mapper.ApplicantApplicationVerifyResponseMapper;
import com.example.universitymanagementsystem.mapper.RegisterApplicantApplicationMapper;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import com.example.universitymanagementsystem.service.CandidateService;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "Applicant", description = "APIs for applicants")
@RestController
@RequestMapping("/applicant")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantApplicationService applicantApplicationService;
    private final SpecialtyAdmissionService specialtyAdmissionService;
    private final CandidateService candidateService;


    private final RegisterApplicantApplicationMapper registerApplicantApplicationMapper;
    private final ApplicantApplicationVerifyResponseMapper applicantApplicationVerifyResponseMapper;

    @Operation(summary = "Register applicant",description = "Register applicant application without activating.The response is applicant id.")
    @PostMapping("/register-applicant")
    public CommonResponseDto<Long> registerApplicant(@RequestBody(required = false) RegisterApplicantApplicationDto requestDto) {
        Long id = applicantApplicationService.registerApplicantApplication(
                registerApplicantApplicationMapper.dtoToEntity(requestDto));

        return new CommonResponseDto<Long>().setData(id).setOk();
    }

    @Operation(description = "Get faculties where specialty admission is available")
    @GetMapping("/get-specialty-admission")
    public CommonResponseDto<Set<Faculty>> getSpecialtyAdmissions(){
        CommonResponseDto<Set<Faculty>> responseDto = new CommonResponseDto<>();
        Set<Faculty> faculties = specialtyAdmissionService.getActiveAdmissions()
                .stream()
                .map(x -> new Faculty(x.getFaculty().getId(), x.getFaculty().getName()))
                .collect(Collectors.toSet());
        responseDto.setOk().setData(faculties);

        return responseDto;
    }

    @Operation(description = "Get specialties by faculty id where admission is available")
    @GetMapping("/get-specialty-admission/{facultyId}")
    public CommonResponseDto<Set<Specialty>> getSpecialtyAdmission(
            @PathVariable Long facultyId
    ){
        Set<Specialty> specialties = specialtyAdmissionService.getActiveAdmissions()
                .stream()
                .filter(x -> x.getFaculty().getId().equals(facultyId))
                .map(x -> new Specialty(x.getSpecialty().getId(), x.getSpecialty().getName()))
                .collect(Collectors.toSet());

        return new CommonResponseDto<Set<Specialty>>().setOk().setData(specialties);
    }

    @Operation(description = "Get candidates to the specialty by id. The response is score and short name")
    @GetMapping("/get-candidates/{specialtyId}")
    public CommonResponseDto<List<CandidatesInfoResponseDto>> getCandidates(
            @PathVariable Long specialtyId
    ){
        List<CandidatesInfoResponseDto> activeCandidateList = candidateService.getAllActiveBySpecId(specialtyId)
                .stream().map(Candidate::getApplicantApplication).map(x -> new CandidatesInfoResponseDto(
                        x.getTestScore(),
                        x.getMiddleName().charAt(0) + x.getFirstName().charAt(0) + x.getLastName()))
                .toList();

        return new CommonResponseDto<List<CandidatesInfoResponseDto>>().setOk().setData(activeCandidateList);
    }

    @GetMapping("/get-all-to-verify")
    public CommonResponseDto<List<ApplicantApplicationVerifyResponseDto>> getToVerify(){
        return new CommonResponseDto<List<ApplicantApplicationVerifyResponseDto>>()
                .setOk()
                .setData(
                        applicantApplicationVerifyResponseMapper
                                .listEntityToDto(applicantApplicationService.getEmailVerifiedApplicants())
                );

    }

}
