package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.DisciplineResponseDto;
import com.example.universitymanagementsystem.mapper.DisciplineResponseMapper;
import com.example.universitymanagementsystem.service.DisciplineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discipline")
@Tag(name = "Discipline APIs")
@RequiredArgsConstructor
public class DisciplineController {
    private final DisciplineService disciplineService;

    private final DisciplineResponseMapper disciplineResponseMapper;

    @GetMapping("/get-all")
    @Operation(summary = "Get all disciplines")
    @PermitAll
    public CommonResponseDto<List<DisciplineResponseDto>> getAll(){
        return new CommonResponseDto<List<DisciplineResponseDto>>()
                .setOk()
                .setData(
                        disciplineResponseMapper.listEntityToDto(
                                disciplineService.getAll())
                );
    }
}
