package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.CurriculumDisciplineResponseDto;
import com.example.universitymanagementsystem.entity.study.CurriculumDiscipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurriculumDisciplineResponseMapper {

    @Mappings({
            @Mapping(target = "curriculumDisciplineId", source = "id"),
            @Mapping(target = "disciplineId", source = "discipline.id"),
            @Mapping(target = "disciplineName", source = "discipline.name"),
            @Mapping(target = "curriculumId", source = "curriculum.id")
    })
    CurriculumDisciplineResponseDto entityToDto(CurriculumDiscipline curriculumDiscipline);

    List<CurriculumDisciplineResponseDto> listEntityToDto(List<CurriculumDiscipline> curriculumDiscipline);
}
