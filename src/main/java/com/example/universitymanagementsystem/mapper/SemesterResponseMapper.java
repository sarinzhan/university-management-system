package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.SemesterResponseDto;
import com.example.universitymanagementsystem.entity.study.Semester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SemesterResponseMapper {

    @Mappings({
            @Mapping(target = "semesterId", source = "id"),
            @Mapping(target = "curriculumId", source = "curriculum.id")
    })
    SemesterResponseDto entityToDto(Semester semester);

    List<SemesterResponseDto> listEntityToDto(List<Semester> semesterList);
}
