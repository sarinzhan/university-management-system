package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.PersonFullNameDto;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;
import com.example.universitymanagementsystem.mapper.PersonFullNameMapper;
import com.example.universitymanagementsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/find-by-pn")
    public PersonFullNameDto findByPN(@RequestParam Long pn) throws PersonNotFoundException {
        try {
            return personFullNameMapper.entityToDto(
                    personService.findByPN(pn));
        }catch (Exception ex){
            return new PersonFullNameDto();
        }
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        return "Hello " + name;
    }
}
