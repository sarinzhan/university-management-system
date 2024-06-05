package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.DisciplineResponseDto;
import com.example.universitymanagementsystem.entity.study.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplineResponseMapper {

    @Mappings({
            @Mapping(target = "disciplineId", source = "id"),
            @Mapping(target = "disciplineName", source = "name"),
    })
    DisciplineResponseDto entityToDto(Discipline discipline);

    List<DisciplineResponseDto> listEntityToDto(List<Discipline> disciplineList);
}
