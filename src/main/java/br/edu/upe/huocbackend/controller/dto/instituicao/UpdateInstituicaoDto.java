package br.edu.upe.huocbackend.controller.dto.instituicao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateInstituicaoDto(
        @NotNull(message = "O ID é obrigatório")
        UUID id,
        @NotBlank(message = "O nome da instituição é obrigatório")
        @Schema(description = "Nome da instituição", example = "Universidade Federal de Pernambuco")
        String nomeInstituicao,
        @NotBlank(message = "O nome do Campos é obrigatório")
        @Schema(description = "Nome do Campos", example = "Palmares")
        String nomeDoCampos,
        @NotBlank(message = "O CNPJ é obrigatório")
       // @CNPJ(message = "CNPJ inválido")
        @Schema(description = "CNPJ da instituição", example = "12.345.678/0001-90")
        String cnpj
    ) {
}
