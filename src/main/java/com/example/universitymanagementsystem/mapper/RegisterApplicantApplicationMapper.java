package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplicationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(imports = {LocalDateTime.class, ApplicantApplicationStatus.class},componentModel = "spring")
public interface RegisterApplicantApplicationMapper {
    RegisterApplicantApplicationMapper instance = Mappers.getMapper(RegisterApplicantApplicationMapper.class);
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(ApplicantApplicationStatus.CREATED)")
    @Mapping(target = "specialty.id", source = "specialtyId")
    @Mapping(target = "faculty.id", source = "facultyId")
    @Mapping(target = "department.id",source = "departmentId")
    @Mapping(target = "specialtyAdmission.id", source = "specialtyAdmissionId")
    ApplicantApplication dtoToEntity(RegisterApplicantApplicationDto dto);
}

