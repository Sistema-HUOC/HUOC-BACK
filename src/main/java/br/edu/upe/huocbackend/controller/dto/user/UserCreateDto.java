package br.edu.upe.huocbackend.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @Schema(description = "Nome do usuário",required = true)
    @NotBlank
    public String nome;

    @Schema(description = "CPF",example = "111.333.222-22", required = true)
    @CPF
    @NotBlank
    public String cpf;

    @Schema(description = "User email address", example = "user@example.com", required = true)
    @Email
    @NotBlank
    public String email;

    @Schema(description = "User password", example = "password123", required = true)
    @NotBlank
    public String password;
}
