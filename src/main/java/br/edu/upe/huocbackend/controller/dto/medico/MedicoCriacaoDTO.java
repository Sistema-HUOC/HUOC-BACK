package br.edu.upe.huocbackend.controller.dto.medico;

import br.edu.upe.huocbackend.controller.dto.user.UserCreateDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Set;

public class MedicoCriacaoDTO extends UserCreateDto {
    @Pattern(regexp = "\\d{4,6}/[A-Z]{2}", message = "CRM deve estar no formato 123456/PE")
    public String crm;

    @NotNull
    public Set<String> especializacoes;
}
