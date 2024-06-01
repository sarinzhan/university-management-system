package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.AdmissionDetailsResponseDto;
import com.example.universitymanagementsystem.dto.response.ApplicantCandidateResponseDto;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdmissionDetailsResponseMapper {
    @Mappings({
            @Mapping(target = "specialtyName", source = "specialtyAdmission.specialty.name"),
            @Mapping(target = "seatNumber", expression = "java(specialtyAdmission.getGroupAmount() * specialtyAdmission.getGroupCapacity())"),
            @Mapping(target = "requiredTestScore", source = "specialtyAdmission.minScore"),
            @Mapping(target = "applicantCandidates", source = "applicantCandidateResponseDto")
    })

    AdmissionDetailsResponseDto entitiesToDto(SpecialtyAdmission specialtyAdmission, List<ApplicantCandidateResponseDto> applicantCandidateResponseDto);
}
