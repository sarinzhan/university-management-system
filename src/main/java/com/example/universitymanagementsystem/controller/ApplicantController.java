package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.dto.response.CandidatesInfoResponseDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.entity.uni_struct.Faculty;
import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import com.example.universitymanagementsystem.exception.ApplicantApplicationAlreadyAppliedException;
import com.example.universitymanagementsystem.exception.CandidateNotFoundException;
import com.example.universitymanagementsystem.exception.SpecialtyAdmissionInvalidException;
import com.example.universitymanagementsystem.exception.SpecialtyAdmissionNotFoundException;
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

    @Operation(summary = "Register applicant",description = "Register applicant application without activating.The response is applicant id.")
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

    @Operation(description = "Get faculties where specialty admission is available")
    @GetMapping("/get-specialty-admission")
    public CommonResponseDto<Set<Faculty>> getSpecialtyAdmissions(){
        CommonResponseDto<Set<Faculty>> responseDto = new CommonResponseDto<>();
        try{
            Set<Faculty> faculties = specialtyAdmissionService.getActiveAdmissions()
                    .stream()
                    .map(x -> new Faculty(x.getFaculty().getId(), x.getFaculty().getName()))
                    .collect(Collectors.toSet());
            responseDto.setStatus(200);
            responseDto.setMessage("OK");
            responseDto.setData(faculties);
            return responseDto;
        } catch (SpecialtyAdmissionNotFoundException ex) {
            responseDto.setStatus(204);
            responseDto.setMessage(ex.getMessage());
            return responseDto;
        }
    }

    @Operation(description = "Get specialties by faculty id where admission is available")
    @GetMapping("/get-specialty-admission/{facultyId}")
    public CommonResponseDto<Set<Specialty>> getSpecialtyAdmission(@PathVariable Long facultyId){
        CommonResponseDto<Set<Specialty>> responseDto = new CommonResponseDto<>();
        try{
            Set<Specialty> specialties = specialtyAdmissionService.getActiveAdmissions()
                    .stream()
                    .filter(x -> x.getFaculty().getId().equals(facultyId))
                    .map(x -> new Specialty(x.getSpecialty().getId(), x.getSpecialty().getName()))
                    .collect(Collectors.toSet());
            responseDto.setData(specialties);
            responseDto.setStatus(200);
            responseDto.setMessage("OK");
            return responseDto;
        }catch (SpecialtyAdmissionNotFoundException ex){
            responseDto.setStatus(204);
            responseDto.setMessage(ex.getMessage());
            return responseDto;
        }
    }

    @Operation(description = "Get candidates to the specialty by id. The response is score and short name")
    @GetMapping("/get-candidates/{specialtyId}")
    public CommonResponseDto<List<CandidatesInfoResponseDto>> getCandidates(@PathVariable Long specialtyId){
        CommonResponseDto<List<CandidatesInfoResponseDto>> responseDto = new CommonResponseDto<>();
        try {
            List<CandidatesInfoResponseDto> activeCandidateList = candidateService.getAllActiveBySpecId(specialtyId)
                    .stream().map(Candidate::getApplicantApplication).map(x -> new CandidatesInfoResponseDto(
                            x.getTestScore(),
                            x.getMiddleName().charAt(0) + x.getFirstName().charAt(0) + x.getLastName()))
                    .toList();
            responseDto.setData(activeCandidateList);
            responseDto.setStatus(200);
            responseDto.setMessage("OK");
            return responseDto;
        }catch (CandidateNotFoundException ex){
            responseDto.setStatus(204);
            responseDto.setMessage(ex.getMessage());
            return responseDto;
        }
    }

}
