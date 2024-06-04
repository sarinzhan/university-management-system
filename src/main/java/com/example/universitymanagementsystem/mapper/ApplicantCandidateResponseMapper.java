package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.ApplicantCandidateResponseDto;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicantCandidateResponseMapper {
    @Mappings({
            @Mapping(target = "firstName", source = "applicantApplication.firstName"),
            @Mapping(target ="secondName", source = "applicantApplication.middleName"),
            @Mapping(target = "lastName", source = "applicantApplication.lastName"),
            @Mapping(target = "testScope", source = "applicantApplication.testScore"),
            @Mapping(target = "isEmailActivated", source = "applicantApplication.isEmailActivated"),
            @Mapping(target = "isChecked", source = "applicantApplication.isChecked")
    })

    ApplicantCandidateResponseDto entitiesToDto(Candidate candidate);

    List<ApplicantCandidateResponseDto> listEntitiesToDto(List<Candidate> candidates);
}
