package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.request.CreateAdmissionRequestDto;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CreateAdmissionRequestMapper {

    @Mappings({
            @Mapping(target = "minScore", source = "minRequiredScore"),
            @Mapping(target = "groupCapacity", source = "seatNumber"),
            @Mapping(target = "specialty.id", source = "specialtyId"),
            @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    })
    SpecialtyAdmission dtoToEntity(CreateAdmissionRequestDto dto);
}
