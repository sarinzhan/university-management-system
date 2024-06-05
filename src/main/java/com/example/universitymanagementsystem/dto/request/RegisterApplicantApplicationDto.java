package com.example.universitymanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@Schema(description = "To register applicants application")
public class RegisterApplicantApplicationDto {

    @Positive(message = "ОРТ балл не может быть меньше 0")
    @NotBlank(message = "ОРТ балл должен быть указан")
    private Integer testScore;

    private Long specialtyAdmissionId;

    @NotBlank(message = "ИНН должен быть указан")
    @Positive(message = "ИНН не может меньше 0")
    private Long personalNumber;

    @Email(message = "Неверный формат почты")
    @NotBlank(message = "Почта должна быть указана")
    private String email;


    private String passportId;

    private String country;

    private String city;

    private String nationality;

    @NotBlank(message = "Пол должен быть указан")
    private String gender;

    @NotNull(message = "Дата рождения не может быть пустым")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Имя должно быть указано")
    private String firstName;

    @NotBlank(message = "Фамилия должна быть указан")
    private String middleName;

    @NotBlank(message = "Отчество должно быть указано")
    private String lastName;

}
