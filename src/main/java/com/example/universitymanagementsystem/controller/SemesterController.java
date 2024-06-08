package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.SemesterResponseDto;
import com.example.universitymanagementsystem.mapper.SemesterResponseMapper;
import com.example.universitymanagementsystem.service.SemesterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/semester")
@RequiredArgsConstructor
@Tag(name = "Semester APIs")
public class SemesterController {
    private final SemesterService semesterService;

    private final SemesterResponseMapper semesterResponseMapper;

    @GetMapping("/get-all-by-group-id/{groupId}")
    @Operation(summary = "Get all semesters by group id")
    @PermitAll
    public CommonResponseDto<List<SemesterResponseDto>> getAllByGroupId(
            @PathVariable Long groupId
    ){
        return new CommonResponseDto<List<SemesterResponseDto>>()
                .setOk()
                .setData(
                        semesterResponseMapper.listEntityToDto(
                                semesterService.getAllByGroupId(groupId)
                        )
                );
    }
}
