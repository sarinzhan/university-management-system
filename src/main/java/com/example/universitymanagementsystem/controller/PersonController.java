package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.PersonDataResponseDto;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;
import com.example.universitymanagementsystem.mapper.PersonDataMapper;
import com.example.universitymanagementsystem.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Person", description = "APIs for person")
@RequestMapping("/person")
@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonDataMapper personFullNameMapper;
    private final PersonService personService;

    @GetMapping("/find-by-pn")
    public CommonResponseDto<PersonDataResponseDto> findByPN(@RequestParam Long pn){
        CommonResponseDto<PersonDataResponseDto> response = new CommonResponseDto<>();
        //TEST
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
