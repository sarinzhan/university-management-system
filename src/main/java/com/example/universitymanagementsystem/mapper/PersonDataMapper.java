package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.PersonDataResponseDto;
import com.example.universitymanagementsystem.entity.PersonData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDataMapper {
    PersonDataResponseDto entityToDto(PersonData entity);
}
