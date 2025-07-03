package br.edu.upe.huocbackend.controller.dto.medico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.util.Set;
import java.util.UUID;

public record UpdateMedicoDto(
        @NotNull(message = "O identificador do médico (huodIdentify) não pode ser nulo")
        @Schema(
                description = "Identificador único do médico no sistema",
                example = "e4d909c2-5c34-4a29-bb70-8bba96b60236",
                required = true
        )
        UUID huodIdentify,
        @Schema(
                description = "Nome completo do médico",
                example = "Ana Carolina Silva",
                required = true
        )
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @Schema(
                description = "CPF do médico (formato: 000.000.000-00)",
                example = "873.582.520-04",
                required = true
        )
        @NotBlank(message = "O CPF não pode estar em branco")
        // @CPF(message = "CPF inválido")
        String cpf,

        @Schema(
                description = "Endereço de e-mail do médico",
                example = "medico@example.com",
                required = true
        )
        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @Schema(
                description = "Número do CRM do médico no formato '123456/UF'",
                example = "123456/PE",
                required = true
        )
        @NotBlank(message = "O CRM não pode estar em branco")
        @Pattern(regexp = "\\d{4,6}/[A-Z]{2}", message = "CRM deve estar no formato 123456/UF")
        String crm,

        @Schema(
                description = "Lista de especializações do médico",
                example = "[\"Cardiologia\", \"Dermatologia\"]",
                required = true
        )
        @NotNull(message = "A lista de especializações não pode ser nula")
        Set<String> especializacoes

) {}
