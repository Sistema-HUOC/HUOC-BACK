package br.edu.upe.huocbackend.controller.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserActivationDTO(@NotBlank String targetUserEmail,@NotNull boolean activate) {
}
