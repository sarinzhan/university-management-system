package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.request.CreateCurriculumRequestDto;
import com.example.universitymanagementsystem.entity.study.Curriculum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring",imports = {LocalDate.class})
public interface CreateCurriculumRequestMapper {

    @Mappings({
            @Mapping(target = "specialty.id",source = "specialtyId"),
            @Mapping(target = "createdDate",expression = "java(LocalDate.now())"),
    })
    Curriculum dtoToEntity(CreateCurriculumRequestDto dto);

}
