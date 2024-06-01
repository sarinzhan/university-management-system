package com.example.universitymanagementsystem.dto.request;

import com.example.universitymanagementsystem.constraint.AdmissionStartBeforeEndConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AdmissionStartBeforeEndConstraint(message = "Дата начала не может быть до даты конца")
@Schema(description = "Dto to create admission")
public class CreateAdmissionRequestDto {
    @NotNull(message = "Специальность должна быть указана")
    private Long specialtyId;

    @Min(value = 1,message = "Количество групп должно быть больше или ровно 1")
    @NotNull(message = "Количество групп должно быть указано")
    private Integer groupAmount;

    @NotNull(message = "Количество мест должно быть указано")
    @Min(value = 1, message = "Количество мест в группе должно быть больше или ровно 1")
    private Integer seatNumber;

    @NotNull(message = "Дата окончания набора должна быть указана")
    @Future(message = "Дата конца должна быть после текущей даты")
    private LocalDateTime endDate;

    @NotNull(message = "Дата начала набора должна быть указана")
    @Future(message = "Дата начала должна быть после текущей даты")
    private LocalDateTime startDate;

    @NotNull(message = "Минимально необходимое количество баллов должно быть указано")
    private Integer minRequiredScore;
}

