package br.edu.upe.huocbackend.controller.dto.login;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {
    @NotBlank
    public String email;
    @NotBlank
    public String password;
}
