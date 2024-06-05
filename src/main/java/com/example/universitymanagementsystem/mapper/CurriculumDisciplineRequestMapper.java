package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.request.CurriculumDisciplineRequestDto;
import com.example.universitymanagementsystem.entity.study.CurriculumDiscipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CurriculumDisciplineRequestMapper {

    @Mapping(target = "discipline.id", source = "disciplineId")
    CurriculumDiscipline dtoToEntity(CurriculumDisciplineRequestDto dto);
}
