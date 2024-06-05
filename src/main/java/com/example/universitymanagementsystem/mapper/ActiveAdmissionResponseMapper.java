package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.ActiveAdmissionResponseDto;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActiveAdmissionResponseMapper {
    @Mappings({
            @Mapping(target = "admissionId", source = "id"),
            @Mapping(target = "specialtyName", source = "specialty.name"),
            @Mapping(target = "facultyName", source = "faculty.name"),
            @Mapping(target = "departmentName", source = "department.name")
    })
    ActiveAdmissionResponseDto entityToDto(SpecialtyAdmission specialtyAdmission);

    List<ActiveAdmissionResponseDto> listEntityToDto(List<SpecialtyAdmission> specialtyAdmissions);
}
