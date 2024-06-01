package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.SpecialtyResponseDto;
import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialtyResponseMapper {

    List<SpecialtyResponseDto> listEntitiesToDto(List<Specialty> specialties);

    @Mappings({
            @Mapping(target = "specialtyId", source = "id"),
            @Mapping(target = "specialtyName", source = "name"),
            @Mapping(target = "departmentId", source = "department.id"),
            @Mapping(target = "departmentName", source = "department.name")
    })
    SpecialtyResponseDto entityToDto(Specialty specialty);
}
