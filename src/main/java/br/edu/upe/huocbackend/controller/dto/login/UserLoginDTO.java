package br.edu.upe.huocbackend.controller.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "User login request")
public class UserLoginDTO {
    @Schema(description = "User email address", example = "user@example.com", required = true)
    @Email
    @NotBlank
    public String email;
    @Schema(description = "User password", example = "password123", required = true)
    @NotBlank
    public String password;
}
