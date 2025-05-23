package br.edu.upe.huocbackend.controller.dto.formularioSintomatologia;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FormularioSintomatologiaDTO {

    @NotBlank(message = "Data obrigatoria")
    private LocalDateTime data;

    @NotBlank(message = "O número do Prontuario é obrigatorio")
    private int numProntuario;

    private String observacoes;

    @NotBlank(message = "O paciente é obrigatório")
    private UUID idPaciente;

    public FormularioSintomatologiaDTO(LocalDateTime data, int numProntuario, String observacoes, UUID idPaciente) {
        this.data = data;
        this.numProntuario = numProntuario;
        this.observacoes = observacoes;
        this.idPaciente = idPaciente;
    }
}
