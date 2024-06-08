package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.request.CreateCourseRequestDto;
import com.example.universitymanagementsystem.entity.study.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface CreateCourseRequestMapper {

    @Mappings({
            @Mapping(source = "course.semesterId", target = "semester.id"),
            @Mapping(source = "course.groupId", target = "group.id"),
            @Mapping(source = "teacherCurriculum.teacherId", target = "teacher.id"),
            @Mapping(source = "teacherCurriculum.curriculumDisciplineId", target = "curriculumDiscipline.id")
    })
    Course dtoToEntity(CreateCourseRequestDto course, CreateCourseRequestDto.TeacherCurriculum teacherCurriculum);

    default List<Course> dtoToListEntity(CreateCourseRequestDto dto) {
        return dto.getTeacherCurriculumList().stream()
                .map(x -> dtoToEntity(dto,x))
                .toList();
    }
}
