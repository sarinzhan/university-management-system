package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.PersonDataResponseDto;
import com.example.universitymanagementsystem.entity.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDataMapper {
    PersonDataResponseDto entityToDto(Person entity);
}
