package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.CurriculumResponseDto;
import com.example.universitymanagementsystem.entity.study.Curriculum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurriculumResponseMapper {

    @Mappings({
            @Mapping(target = "curriculumId", source = "id"),
            @Mapping(target = "specialtyId", source = "specialty.id"),
            @Mapping(target = "specialtyName", source = "specialty.name")
    })
    CurriculumResponseDto entityToDto(Curriculum curriculum);

    List<CurriculumResponseDto> listEntityToDto(List<Curriculum> curriculumList);
}
