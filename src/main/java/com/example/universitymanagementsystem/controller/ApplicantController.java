package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.dto.response.ActiveSpecialtyRecruitmentResponceDto;
import com.example.universitymanagementsystem.dto.response.CandidatesInfoResponseDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public CommonResponseDto<Set<ActiveSpecialtyRecruitmentResponceDto>> getSpecialtyAdmissions(){
        CommonResponseDto<Set<ActiveSpecialtyRecruitmentResponceDto>> responseDto = new CommonResponseDto<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        try{
            Set<ActiveSpecialtyRecruitmentResponceDto> activeSpecialtyRecruitment = specialtyAdmissionService.getActiveAdmissions()
                    .stream()
                    .filter(x -> x.getStartDate().isAfter(localDateTime)
                            && x.getEndDate().isBefore(localDateTime))
                    .map(x -> {
                        Specialty specialty = x.getSpecialty();
                        return new ActiveSpecialtyRecruitmentResponceDto(specialty.getId(), specialty.getName(), x.getGroupCapacity());
                    })
                    .collect(Collectors.toSet());

            responseDto.setStatus(200);
            responseDto.setMessage("OK");
            responseDto.setData(activeSpecialtyRecruitment);
            return responseDto;
        } catch (SpecialtyAdmissionNotFoundException ex) {
            responseDto.setStatus(204);
            responseDto.setMessage(ex.getMessage());
            return responseDto;
        }
    }

    @Operation(description = "Get specialties by faculty id where admission is available")
    @GetMapping("/get-specialty-admission/{facultyId}")
    public CommonResponseDto<Set<ActiveSpecialtyRecruitmentResponceDto>> getSpecialtyAdmission(@PathVariable Long facultyId){
        CommonResponseDto<Set<ActiveSpecialtyRecruitmentResponceDto>> responseDto = new CommonResponseDto<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        try{
            Set<ActiveSpecialtyRecruitmentResponceDto> activeSpecialtyRecruitment = specialtyAdmissionService.getActiveAdmissions()
                    .stream()
                    .filter(x -> x.getStartDate().isAfter(localDateTime)
                            && x.getEndDate().isBefore(localDateTime)
                            && x.getFaculty().getId().equals(facultyId))
                    .map(x -> {
                        Specialty specialty = x.getSpecialty();
                        return new ActiveSpecialtyRecruitmentResponceDto(specialty.getId(), specialty.getName(), x.getGroupCapacity());
                    })
                    .collect(Collectors.toSet());

            responseDto.setData(activeSpecialtyRecruitment);
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
                    .stream()
                    .map(Candidate::getApplicantApplication)
                    .map(x -> new CandidatesInfoResponseDto(
                            x.getFirstName(),
                            x.getMiddleName(),
                            x.getLastName(),
                            x.getTestScore(),
                            false))
                    .sorted(Comparator.comparingDouble(CandidatesInfoResponseDto::getTestScore))
                    .toList()
                    .reversed();

            SpecialtyAdmission specialtyAdmission = specialtyAdmissionService.getActiveAdmissions()
                    .stream()
                    .filter(x -> x.getSpecialty().getId().equals(specialtyId))
                    .findFirst()
                    .orElse(null);

            int seatsNumber = specialtyAdmission.getGroupCapacity() * specialtyAdmission.getGroupAmount();

            activeCandidateList
                    .stream()
                    .limit(seatsNumber)
                    .forEach(x -> x.setIsAdmitted(true));

            responseDto.setData(activeCandidateList);
            responseDto.setStatus(200);
            responseDto.setMessage("OK");
            return responseDto;
        }catch (Exception ex){
            responseDto.setStatus(204);
            responseDto.setMessage(ex.getMessage());
            return responseDto;
        }
    }

}
