package br.edu.upe.huocbackend.controller.dto.enfermagem;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Schema(description = "DTO para atualização de dados de um(a) enfermeiro(a)")
public record UpdateEnfermeiroDTO(

        @NotNull(message = "O identificador do enfermeiro(a) não pode ser nulo")
        @Schema(description = "ID único do enfermeiro(a) no sistema", example = "1f3e1d7e-854f-43c1-b5f5-d81a1a0cb9a6")
        UUID huodIdentify,

        @NotBlank(message = "O nome não pode estar em branco")
        @Schema(description = "Nome completo do enfermeiro(a)", example = "Maria Oliveira")
        String nome,

        @NotBlank(message = "O CPF não pode estar em branco")
        // @CPF(message = "CPF inválido") // Descomente se usar Hibernate Validator
        @Schema(description = "CPF do enfermeiro(a)", example = "123.456.789-00")
        String cpf,

        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "Formato de e-mail inválido")
        @Schema(description = "E-mail do enfermeiro(a)", example = "enfermeira@example.com")
        String email,

        @NotBlank(message = "O COREN não pode estar em branco")
        @Schema(description = "Número do COREN do enfermeiro(a)", example = "123456-PE")
        String coren

) {}
