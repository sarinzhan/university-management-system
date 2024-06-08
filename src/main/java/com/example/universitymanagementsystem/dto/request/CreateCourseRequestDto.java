package com.example.universitymanagementsystem.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateCourseRequestDto {

    @NotNull(message = "Необходимо указать семестер")
    private Long semesterId;

    @NotNull(message = "Необходимо указать группу")
    private Long groupId;

    @NotEmpty(message = "Необходимо заполнить все поля")
    @Valid
    List<TeacherCurriculum> teacherCurriculumList;

    @Data
    public static class TeacherCurriculum {

        @NotNull(message = "Необходимо указать преподавателя")
        private Long teacherId;

        @NotNull(message = "Необходимо указать план обучения")
        private Long curriculumDisciplineId;
    }
}
