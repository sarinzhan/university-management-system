package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.PersonFullNameDto;
import com.example.universitymanagementsystem.entity.PersonData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonFullNameMapper {
    PersonFullNameMapper INSTANCE = Mappers.getMapper(PersonFullNameMapper.class);
    PersonFullNameDto entityToDto(PersonData entity);
}
