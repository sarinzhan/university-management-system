package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.request.CreateCurriculumRequestDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.entity.study.Curriculum;
import com.example.universitymanagementsystem.mapper.CreateCurriculumRequestMapper;
import com.example.universitymanagementsystem.mapper.CurriculumDisciplineRequestMapper;
import com.example.universitymanagementsystem.service.CurriculumDisciplineService;
import com.example.universitymanagementsystem.service.CurriculumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Curriculum APIs")
@RequiredArgsConstructor
@RequestMapping("/curriculum")
public class CurriculumController {
    private final CurriculumService curriculumService;
    private final CurriculumDisciplineService curriculumDisciplineService;

    private final CurriculumDisciplineRequestMapper curriculumDisciplineRequestMapper;
    private final CreateCurriculumRequestMapper createCurriculumRequestMapper;

    @PostMapping("/create")
    @Operation(summary = "Create curriculum")
    @PreAuthorize("hasAnyRole('CURRICULUM_DEPARTMENT')")
    public CommonResponseDto<Long> create(
            @Valid @RequestBody CreateCurriculumRequestDto curriculumRequestDto
    ){
        Curriculum savedCurriculum = curriculumService.create(
                createCurriculumRequestMapper.dtoToEntity(curriculumRequestDto));

        curriculumRequestDto.getCurriculumDisciplineRequestList()
                .stream()
                .map(curriculumDisciplineRequestMapper::dtoToEntity)
                .peek(x -> x.setCurriculum(savedCurriculum))
                .forEach(curriculumDisciplineService::create);

        return new CommonResponseDto<Long>()
                .setOk()
                .setData(savedCurriculum.getId());
    }
}
