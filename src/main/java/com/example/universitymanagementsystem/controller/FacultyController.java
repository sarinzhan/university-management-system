package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.FacultyResponseDto;
import com.example.universitymanagementsystem.mapper.FacultyResponseMapper;
import com.example.universitymanagementsystem.service.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Faculty API", description = "APIs for faculties")
@RequestMapping("/faculty")
@RestController
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;
    private final FacultyResponseMapper facultyResponseMapper;

    @Operation(summary = "Get all faculties")
    @GetMapping("/get-all")
    @PermitAll
    public CommonResponseDto<List<FacultyResponseDto>> getAllFaculties(){
        return new CommonResponseDto<List<FacultyResponseDto>>()
                .setOk()
                .setData(
                        facultyResponseMapper.listEntitiesToDto(
                                facultyService.getAllFaculties())
                );
    }


}
