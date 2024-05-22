package com.example.universitymanagementsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class RegisterApplicantApplicationDto {
    @NotBlank(message = "ОРТ балл должен быть указан")
    private Integer testScore;

    private Long specialtyAdmissionId;

    private Long specialtyId;

    private Long facultyId;

    private Long departmentId;

    @NotBlank(message = "ИНН должен быть указан")
    private Long personalNumber;

    @Email(message = "Неверный формат почты")
    @NotBlank(message = "Почта должна быть указана")
    private String email;

    private String passportId;

    private String country;

    private String city;

    private LocalDate dateOfBirth;

    @NotBlank(message = "Имя должно быть указано")
    private String firstName;

    @NotBlank(message = "Фамилия должна быть указана")
    private String middleName;

    @NotBlank(message = "Отчество должно быть указано")
    private String lastName;

    @NotBlank(message = "Пол должен быть указан")
    private String gender;
}
