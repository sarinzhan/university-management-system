package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.FacultyResponseDto;
import com.example.universitymanagementsystem.entity.uni_struct.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyResponseMapper {
    @Mappings({
            @Mapping(target = "facultyId", source = "id"),
            @Mapping(target = "facultyName", source = "name")
    })
    FacultyResponseDto entityToDto(Faculty faculty);

    List<FacultyResponseDto> listEntitiesToDto(List<Faculty> faculties);
}
