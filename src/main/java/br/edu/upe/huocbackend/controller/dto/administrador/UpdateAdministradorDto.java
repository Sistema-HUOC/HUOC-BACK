package br.edu.upe.huocbackend.controller.dto.administrador;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Schema(description = "DTO para atualização de dados do administrador")
public record UpdateAdministradorDto(

        @NotNull(message = "O identificador do administrador não pode ser nulo")
        @Schema(description = "Identificador único do administrador", example = "4e3f6bb4-2f9b-4e97-8f36-56264c848914")
        UUID huodIdentify,

        @NotBlank(message = "O nome não pode estar em branco")
        @Schema(description = "Nome completo do administrador", example = "João da Silva")
        String nome,

        @NotBlank(message = "O CPF não pode estar em branco")
        // @CPF(message = "CPF inválido") // opcional se quiser validação formal
        @Schema(description = "CPF do administrador (formato: 000.000.000-00)", example = "123.456.789-00")
        String cpf,

        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "Formato de e-mail inválido")
        @Schema(description = "Endereço de e-mail do administrador", example = "admin@example.com")
        String email

) {}
