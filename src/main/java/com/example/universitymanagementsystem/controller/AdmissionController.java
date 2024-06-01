package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.*;
import com.example.universitymanagementsystem.mapper.*;
import com.example.universitymanagementsystem.service.CandidateService;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admission")
@RequiredArgsConstructor
@Tag(name = "Admissions API", description = "APIs for specialty admission")
public class AdmissionController {

    private final SpecialtyAdmissionService specialtyAdmissionService;
    private final CandidateService candidateService;

    private final SpecialtyAdmissionResponseMapper specialtyAdmissionResponseMapper;
    private final FacultyAdmissionResponseMapper facultyAdmissionResponseMapper;
    private final AdmissionResponseMapper admissionResponseMapper;
    private final AdmissionDetailsResponseMapper admissionDetailsResponseMapper;
    private final ApplicantCandidateResponseMapper applicantCandidateResponseMapper;;

    @Operation(summary = "Get faculty admission",description = "Get faculties where specialty admission is available")
    @GetMapping("/get-faculties")
    public CommonResponseDto<Set<FacultyAdmissionResponseDto>> getFacultiesAdmissions(){
        return new CommonResponseDto<Set<FacultyAdmissionResponseDto>>()
                .setOk()
                .setData(
                        new HashSet<>(facultyAdmissionResponseMapper.listEntityToDto(
                                specialtyAdmissionService.getActiveAdmissions()))
                );
    }

    @Operation(summary = "Get specialty admission",description = "Get specialties by faculty id where admission is available")
    @GetMapping("/get-specialty/{facultyId}")
    public CommonResponseDto<List<SpecialtyAdmissionResponseDto>> getSpecialtyAdmission(
            @PathVariable Long facultyId
    ){
        return new CommonResponseDto<List<SpecialtyAdmissionResponseDto>>()
                .setOk()
                .setData(
                        specialtyAdmissionResponseMapper.listEntityToDto(
                                specialtyAdmissionService.getActiveAdmissions(facultyId))
                );
    }

    @Operation(summary = "Get all admissions", description = "Get all the admissions for all the time")
    @GetMapping("/get-admission-list")
    public CommonResponseDto<List<AdmissionResponseDto>> getAllAdmissions(){
        return  new CommonResponseDto<List<AdmissionResponseDto>>()
                .setOk()
                .setData(
                        admissionResponseMapper.listEntityToDto(
                                specialtyAdmissionService.getAllAdmissions())
                );
    }

    @Operation(summary = "Get information about admission", description = "Get information about recruitment by id")
    @GetMapping("/get-admission-details/{admissionId}")
    public CommonResponseDto<AdmissionDetailsResponseDto> getAdmissionDetails(
            @PathVariable Long admissionId
    ){
        List<ApplicantCandidateResponseDto> applicantCandidates = applicantCandidateResponseMapper.listEntitiesToDto(
                candidateService.getAllActiveByAdmissionId(admissionId));

        AdmissionDetailsResponseDto admissionDetails = admissionDetailsResponseMapper.entitiesToDto(
                specialtyAdmissionService.getAdmissionById(admissionId), applicantCandidates);

        return new CommonResponseDto<AdmissionDetailsResponseDto>()
                .setOk()
                .setData(
                        admissionDetails
                );
    }
}
