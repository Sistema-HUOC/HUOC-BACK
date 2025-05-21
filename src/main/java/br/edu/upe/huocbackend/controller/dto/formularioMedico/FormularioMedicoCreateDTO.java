package br.edu.upe.huocbackend.controller.dto.formularioMedico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "FormularioMedicoCreateDTO")
public class FormularioMedicoCreateDTO {

    @NotBlank
    public UUID id;
    @NotNull
    public LocalDateTime data;
    @NotBlank
    public String observacoesAdicionaisFormularioMedico;


    public FormularioMedicoCreateDTO(LocalDateTime data, String observacoesAdicionaisFormularioMedico) {
        this.data = data;
        this.observacoesAdicionaisFormularioMedico = observacoesAdicionaisFormularioMedico;
    }
}
