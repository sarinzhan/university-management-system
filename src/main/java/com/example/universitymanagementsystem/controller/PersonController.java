package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.PersonFullNameDto;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;
import com.example.universitymanagementsystem.mapper.PersonFullNameMapper;
import com.example.universitymanagementsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/person")
@RestController
public class PersonController{

    private final PersonFullNameMapper personFullNameMapper;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonFullNameMapper personFullNameMapper, PersonService personService) {
        this.personFullNameMapper = personFullNameMapper;
        this.personService = personService;
    }

    @GetMapping("/find-by-pn/{pn}")
    public PersonFullNameDto findByPN(@PathVariable Long pn) throws PersonNotFoundException {
            return new PersonFullNameDto();

    }


}
