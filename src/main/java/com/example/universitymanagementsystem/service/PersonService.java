package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.dto.response.PersonFullNameDto;
import com.example.universitymanagementsystem.entity.PersonData;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;

import java.util.Optional;

public interface PersonService {
    PersonData findByPN(Long pn) throws PersonNotFoundException;
}
