package com.example.universitymanagementsystem.dto.request;

import com.example.universitymanagementsystem.entity.study.CurriculumDiscipline;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Create curriculum for the specialty for the specified semester")
public class CreateCurriculumRequestDto {

    @NotNull(message = "Специальность должна быть указана")
    private Long specialtyId;

    @NotNull(message = "Номер семестра должен быть указан")
    private Long semesterNumber;

    @Size(min = 1, max = 10, message = "Идентифицирующие название учебного плана должна содержать от 1 до 10 символов")
    @NotBlank(message = "Идентифицирующие название плана должна быть указана")
    private String identifierName;

    @NotEmpty(message = "Список дисциплин не должен быть пустым")
    @Valid
    List<CurriculumDisciplineRequestDto> curriculumDisciplineRequestList;

}
