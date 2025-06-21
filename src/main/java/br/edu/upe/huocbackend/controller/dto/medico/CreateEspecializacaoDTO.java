package br.edu.upe.huocbackend.controller.dto.medico;

import jakarta.validation.constraints.NotBlank;

public record CreateEspecializacaoDTO(@NotBlank String especializacao) {
}
