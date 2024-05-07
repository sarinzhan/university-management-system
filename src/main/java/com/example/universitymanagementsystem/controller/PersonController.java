package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.PersonFullNameDto;
import com.example.universitymanagementsystem.entity.PersonData;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;
import com.example.universitymanagementsystem.mapper.PersonFullNameMapper;
import com.example.universitymanagementsystem.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Person", description = "APIs for person")
@RequestMapping("/person")
@RestController
public class PersonController {

    private final PersonFullNameMapper personFullNameMapper;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonFullNameMapper personFullNameMapper, PersonService personService) {
        this.personFullNameMapper = personFullNameMapper;
        this.personService = personService;
    }

    @GetMapping("/find-by-pn")
    public CommonResponseDto<PersonFullNameDto> findByPN(@RequestParam Long pn){
        CommonResponseDto<PersonFullNameDto> response = new CommonResponseDto<>();
        try {
            response.setData(personFullNameMapper
                    .entityToDto(personService.findByPN(pn)));
            response.setStatus(200);
            response.setMessage("OK");
            return response;
        } catch (PersonNotFoundException ex) {
            response.setStatus(204);
            response.setMessage("No content");
            return response;
        }
    }
}
