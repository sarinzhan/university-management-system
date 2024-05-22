package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CandidateDistributionResponseDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.FacultyAdmissionResponseDto;
import com.example.universitymanagementsystem.dto.response.SpecialtyAdmissionResponseDto;
import com.example.universitymanagementsystem.mapper.CandidateDistributionResponseMapper;
import com.example.universitymanagementsystem.mapper.FacultyAdmissionResponseMapper;
import com.example.universitymanagementsystem.mapper.SpecialtyAdmissionResponseMapper;
import com.example.universitymanagementsystem.service.CandidateDistributionService;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/specialty-admission")
@RequiredArgsConstructor
@Tag(name = "Specialty admission", description = "APIs for specialty admission")
public class AdmissionController {

    private final SpecialtyAdmissionService specialtyAdmissionService;
    private final CandidateDistributionService candidateDistributionService;

    private final SpecialtyAdmissionResponseMapper specialtyAdmissionResponseMapper;
    private final FacultyAdmissionResponseMapper facultyAdmissionResponseMapper;
    private final CandidateDistributionResponseMapper candidateDistributionResponseMapper;


    @Operation(summary = "Get faculty admission",description = "Get faculties where specialty admission is available")
    @GetMapping("/get-faculties-admission")
    @PermitAll
    public CommonResponseDto<Set<FacultyAdmissionResponseDto>> getFacultiesAdmissions(){
        return new CommonResponseDto<Set<FacultyAdmissionResponseDto>>()
                .setOk()
                .setData(
                        new HashSet<>(facultyAdmissionResponseMapper.listEntityToDto(
                                specialtyAdmissionService.getActiveAdmissions()))
                );
    }

    @Operation(summary = "Get specialty admission",description = "Get specialties by faculty id where admission is available")
    @GetMapping("/get-specialty-admission/{facultyId}")
    @PermitAll
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

    @Operation(summary = "Distribute candidates", description = "Create groups. Create students. Create for students accounts and send message to the email")
    @GetMapping("/distribute/{admissionId}")
    @PermitAll
    public CommonResponseDto<List<CandidateDistributionResponseDto>> distribution(
            @PathVariable Long admissionId
    ){
        List<CandidateDistributionResponseDto> candidateDistributionResponseDtoList = candidateDistributionService.distributeCandidates(admissionId);
        return new CommonResponseDto<List<CandidateDistributionResponseDto>>()
                .setOk()
                .setData(candidateDistributionResponseDtoList);
    }


}
