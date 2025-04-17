package br.com.HUOC_BACK.controller.dto;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class FormularioSintomatologiaDTO {
    @NotBlank
    private LocalDateTime data;

    @NotBlank
    private int numProntuario;

    @NotBlank
    private String observacoes;


    /// Alterar para devolver o nome do paciente.
    @NotBlank
    private String id_Paciente;
}
