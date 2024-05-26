package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.AdmissionResponseDto;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdmissionResponseDtoMapper {
    @Mappings({
            @Mapping(target = "specialtyName", source = "faculty.name"),
            @Mapping(target = "isActive", constant = "false")
    })
    AdmissionResponseDto entityToDto(SpecialtyAdmission specialtyAdmission);

    List<AdmissionResponseDto> listEntityToDto(List<SpecialtyAdmission> specialtyAdmissions);
}
