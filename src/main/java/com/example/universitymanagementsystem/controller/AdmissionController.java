package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.*;
import com.example.universitymanagementsystem.mapper.*;
import com.example.universitymanagementsystem.service.CandidateService;
import com.example.universitymanagementsystem.dto.request.CreateAdmissionRequestDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.FacultyAdmissionResponseDto;
import com.example.universitymanagementsystem.dto.response.SpecialtyAdmissionResponseDto;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.mapper.CreateAdmissionRequestMapper;
import com.example.universitymanagementsystem.dto.response.CandidateDistributionResponseDto;
import com.example.universitymanagementsystem.mapper.FacultyAdmissionResponseMapper;
import com.example.universitymanagementsystem.mapper.SpecialtyAdmissionResponseMapper;
import com.example.universitymanagementsystem.service.CandidateDistributionService;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admission")
@RequiredArgsConstructor
@Tag(name = "Admissions API", description = "APIs for specialty admission")
public class AdmissionController {

    private final SpecialtyAdmissionService specialtyAdmissionService;
    private final CandidateDistributionService candidateDistributionService;
    private final CandidateService candidateService;

    private final SpecialtyAdmissionResponseMapper specialtyAdmissionResponseMapper;
    private final FacultyAdmissionResponseMapper facultyAdmissionResponseMapper;
    private final AdmissionResponseMapper admissionResponseMapper;
    private final AdmissionDetailsResponseMapper admissionDetailsResponseMapper;
    private final ApplicantCandidateResponseMapper applicantCandidateResponseMapper;
    private final CreateAdmissionRequestMapper createAdmissionRequestMapper;


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
    @GetMapping("/get/{facultyId}")
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
    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('ADMISSION_COMMISSION')")
    public CommonResponseDto<List<AdmissionResponseDto>> getAllAdmissions(){
        return new CommonResponseDto<List<AdmissionResponseDto>>()
                .setOk()
                .setData(
                        admissionResponseMapper.listEntityToDto(
                                specialtyAdmissionService.getAllAdmissions())
                );
    }

    @Operation(summary = "Get information about admission", description = "Get information about recruitment by id")
    @GetMapping("/get-details/{admissionId}")
    @PreAuthorize("hasAnyRole('ADMISSION_COMMISSION')")
    public CommonResponseDto<AdmissionDetailsResponseDto> getAdmissionDetails(
            @PathVariable Long admissionId
    ) {
        List<ApplicantCandidateResponseDto> applicantCandidates = applicantCandidateResponseMapper.listEntitiesToDto(
                candidateService.getAllActiveByAdmissionId(admissionId));

        AdmissionDetailsResponseDto admissionDetails = admissionDetailsResponseMapper.entitiesToDto(
                specialtyAdmissionService.getAdmissionById(admissionId), applicantCandidates);

        return new CommonResponseDto<AdmissionDetailsResponseDto>()
                .setOk()
                .setData(admissionDetails);
    }

    @Operation(summary = "Create new admission")
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMISSION_COMMISSION')")
    public CommonResponseDto<Long> createAdmission(
            @Valid @RequestBody CreateAdmissionRequestDto dto
    ){
        SpecialtyAdmission sourceAdmission = createAdmissionRequestMapper.dtoToEntity(dto);
        Long admissionId = specialtyAdmissionService.create(sourceAdmission);

        return new CommonResponseDto<Long>()
                .setOk()
                .setData(admissionId);
    }

    @Operation(summary = "Distribute candidates", description = "Create groups. Create students. Create for students accounts and send message to the email")
    @GetMapping("/distribute/{admissionId}")
    @PreAuthorize("hasAnyRole('ADMISSION_COMMISSION')")
    public CommonResponseDto<List<CandidateDistributionResponseDto>> distribution(
            @PathVariable Long admissionId
    ){
        List<CandidateDistributionResponseDto> candidateDistributionResponseDtoList = candidateDistributionService.distributeCandidates(admissionId);
        return new CommonResponseDto<List<CandidateDistributionResponseDto>>()
                .setOk()
                .setData(candidateDistributionResponseDtoList);
    }


    @Operation(summary = "Planned admissions")
    @GetMapping("/planned")
    @PreAuthorize("hasAnyRole('ADMISSION_COMMISSION')")
    public CommonResponseDto<List<AdmissionResponseDto>> getPlanned(){
        List<SpecialtyAdmission> specialtyAdmissionList =  specialtyAdmissionService.getAllPlanned();
        List<AdmissionResponseDto> admissionResponseDtoList =  admissionResponseMapper.listEntityToDto(specialtyAdmissionList);
        return new CommonResponseDto<List<AdmissionResponseDto>>()
                .setOk()
                .setData(admissionResponseDtoList);
    }


}
