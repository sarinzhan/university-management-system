package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.entity.Person;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EntityToEntityMapper {
    Person applicantApplicationToPersonData(ApplicantApplication applicantApplication);

    Person personToPerson(Person person);
}
