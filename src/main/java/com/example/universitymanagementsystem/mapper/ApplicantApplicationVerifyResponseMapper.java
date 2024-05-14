package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.ApplicantApplicationVerifyResponseDto;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import jakarta.persistence.ManyToOne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicantApplicationVerifyResponseMapper {
    @Mappings({
        @Mapping(target = "specialtyName",source = "specialty.name"),
        @Mapping(target = "facultyName",source = "faculty.name"),
        @Mapping(target = "departmentName",source = "department.name")
    })
    ApplicantApplicationVerifyResponseDto entityToDto(ApplicantApplication entity);

    List<ApplicantApplicationVerifyResponseDto> listEntityToDto(List<ApplicantApplication> entity);
}
