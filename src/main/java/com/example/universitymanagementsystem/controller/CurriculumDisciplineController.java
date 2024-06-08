package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.CurriculumDisciplineResponseDto;
import com.example.universitymanagementsystem.mapper.CurriculumDisciplineResponseMapper;
import com.example.universitymanagementsystem.service.CurriculumDisciplineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/curriculum-discipline")
@RequiredArgsConstructor
@Tag(name = "Curriculum discipline APIs")
public class CurriculumDisciplineController {
    private final CurriculumDisciplineService curriculumDisciplineService;

    private final CurriculumDisciplineResponseMapper curriculumDisciplineResponseMapper;
    @GetMapping("/get-all-by-curriculum-id/{curriculumId}")
    @Operation(summary = "Get all discipline to the curriculum by curriculum id")
    @PermitAll
    public CommonResponseDto<List<CurriculumDisciplineResponseDto>> getAllByCurriculumId(
            @PathVariable Long curriculumId
    ){
        return new CommonResponseDto<List<CurriculumDisciplineResponseDto>>()
                .setOk()
                .setData(
                        curriculumDisciplineResponseMapper.listEntityToDto(
                                curriculumDisciplineService.getAllByCurriculumId(curriculumId)
                        )
                );
    }
}
