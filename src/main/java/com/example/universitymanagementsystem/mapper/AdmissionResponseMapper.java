package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.AdmissionResponseDto;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdmissionResponseMapper {
    @Mappings({
            @Mapping(target = "specialtyName", source = "specialty.name"),
            @Mapping(target = "facultyName", source = "faculty.name")
    })
    AdmissionResponseDto entityToDto(SpecialtyAdmission specialtyAdmission);

    List<AdmissionResponseDto> listEntityToDto(List<SpecialtyAdmission> specialtyAdmissions);
}
