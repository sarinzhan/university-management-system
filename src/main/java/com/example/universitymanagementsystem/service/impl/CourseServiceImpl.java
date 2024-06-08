package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.study.Course;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.CourseRepository;
import com.example.universitymanagementsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public List<Course> addAll(Iterable<Course> iterableCourse) {
        try {
            return courseRepository.saveAll(iterableCourse);
        }catch (Exception ex){
            throw new BaseBusinessLogicException("Не удалось сохранить курсы");
        }
    }
}
