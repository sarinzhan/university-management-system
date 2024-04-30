package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.PersonFullNameDto;
import com.example.universitymanagementsystem.entity.PersonData;

public class PersonFullNameDtoMapper {
    public static PersonFullNameDto entityToDto(PersonData entity){
        return new PersonFullNameDto(
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName()
        );
    }
}
