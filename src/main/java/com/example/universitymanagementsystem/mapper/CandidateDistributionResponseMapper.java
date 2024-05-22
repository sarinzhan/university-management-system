package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.CandidateDistributionResponseDto;
import com.example.universitymanagementsystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CandidateDistributionResponseMapper {

    @Mappings({
            @Mapping(target = "studentId", source = "id"),
            @Mapping(target = "specialtyName", source = "application.specialty.name"),
            @Mapping(target = "firstName", source = "person.firstName"),
            @Mapping(target = "middleName", source = "person.middleName"),
            @Mapping(target = "lastName", source = "person.lastName"),
            @Mapping(target = "groupId", source = "group.id"),
            @Mapping(target = "groupName", source = "group.name"),
            @Mapping(target = "testScore", source = "application.testScore"),
            @Mapping(target = "admissionId", source = "application.specialtyAdmission.id")
    })
    CandidateDistributionResponseDto entityToDto(Student student);
}
