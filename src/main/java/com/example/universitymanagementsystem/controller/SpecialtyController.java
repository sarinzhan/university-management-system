package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.SpecialtyResponseDto;
import com.example.universitymanagementsystem.mapper.SpecialtyResponseMapper;
import com.example.universitymanagementsystem.service.SpecialtyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Specialty API", description = "APIs for specialties")
@RequestMapping("/specialty")
@RestController
@RequiredArgsConstructor
public class SpecialtyController {
    private final SpecialtyService specialtyService;
    private final SpecialtyResponseMapper specialtyResponseMapper;

    @GetMapping("/get-all")
    public CommonResponseDto<List<SpecialtyResponseDto>> getAllSpecialties(){
        return new CommonResponseDto<List<SpecialtyResponseDto>>()
                .setOk()
                .setData(
                        specialtyResponseMapper.listEntitiesToDto(
                                specialtyService.getAll())
                );
    }
}
