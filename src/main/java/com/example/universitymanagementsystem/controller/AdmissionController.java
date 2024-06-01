package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.CreateAdmissionRequestDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.FacultyAdmissionResponseDto;
import com.example.universitymanagementsystem.dto.response.SpecialtyAdmissionResponseDto;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.mapper.CreateAdmissionRequestMapper;
import com.example.universitymanagementsystem.mapper.FacultyAdmissionResponseMapper;
import com.example.universitymanagementsystem.mapper.SpecialtyAdmissionResponseMapper;
import com.example.universitymanagementsystem.service.SpecialtyAdmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    private final SpecialtyAdmissionResponseMapper specialtyAdmissionResponseMapper;
    private final FacultyAdmissionResponseMapper facultyAdmissionResponseMapper;
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
}
