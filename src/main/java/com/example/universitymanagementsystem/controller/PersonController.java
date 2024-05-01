package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.PersonFullNameDto;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;
import com.example.universitymanagementsystem.mapper.PersonFullNameMapper;
import com.example.universitymanagementsystem.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(name = "/findByPN")
    public PersonFullNameDto findByPN(@RequestParam Long pn) throws PersonNotFoundException {
        int a = 10;
        return PersonFullNameMapper.INSTANCE.entityToDto(
                personService
                        .findByPN(pn)

        );
    }
}
