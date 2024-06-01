package com.example.universitymanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Schema(description = "Запрос на аутентификацию")
@Data
public class LoginRequestDto {
    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(min = 8, max = 30, message = "Длина пароля должна быть от 8 до 30 символов")
    private String password;

    @Schema(description = "Имя пользователя", example = "Jon")
    @Size(min = 5, max = 20, message = "Имя пользователя должно содержать от 5 до 20 символов")
    private String login;
}
