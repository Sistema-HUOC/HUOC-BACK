package br.edu.upe.huocbackend.controller.dto.medico;

import br.edu.upe.huocbackend.controller.dto.user.UserCreateDto;
import br.edu.upe.huocbackend.model.Especializacoes;
import jakarta.validation.constraints.Pattern;

public class MedicoCriacaoDTO extends UserCreateDto {
    @Pattern(regexp = "\\d{4,6}/[A-Z]{2}", message = "CRM deve estar no formato 123456/PE")
    public String crm;

    public Especializacoes especializacoes;
}
