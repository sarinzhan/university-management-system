package com.example.universitymanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "")
public class CurriculumDisciplineRequestDto {

    @NotNull(message = "Количество часов для дисциплин должна быть указана")
    private Integer hour;

    @NotNull(message = "Дисциплина должна быть указана")
    private Long disciplineId;
}
