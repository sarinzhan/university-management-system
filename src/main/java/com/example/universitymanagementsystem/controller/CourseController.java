package com.example.universitymanagementsystem.controller;


import com.example.universitymanagementsystem.dto.request.CreateCourseRequestDto;
import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.entity.study.Course;
import com.example.universitymanagementsystem.mapper.CreateCourseRequestMapper;
import com.example.universitymanagementsystem.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
@Tag(name = "Course APIs")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    private final CreateCourseRequestMapper createCourseRequestMapper;

    @PostMapping("/create")
    @Operation(summary = "Create course")
    public CommonResponseDto<Long> create(
            @Valid @RequestBody CreateCourseRequestDto sourceDto
    ){
        List<Course> courses = createCourseRequestMapper.dtoToListEntity(sourceDto);
        courseService.addAll(courses);
        return new CommonResponseDto<Long>()
                .setOk();
    }
}
