package com.example.universitymanagementsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Schema(description = "To register applicants application")
public class RegisterApplicantApplicationDto {

    @Positive(message = "ОРТ балл не может быть меньше 0")
    private Integer testScore;

    private Long specialtyAdmissionId;

    @Positive(message = "ИНН не может меньше 0")
    private Long personalNumber;

    @Email(message = "Неправильный формат email")
    private String email;

    @NotBlank(message = "ID паспорта не может быть пустым")
    private String passportId;

    private String country;

    private String nationality;

    @NotBlank(message = "Не может быть пустым")
    private String gender;

    @NotNull(message = "Дата рождения не может быть пустым")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустым")
    private String middleName;

    @NotBlank(message = "Отчество не может быть пустым")
    private String lastName;
}
