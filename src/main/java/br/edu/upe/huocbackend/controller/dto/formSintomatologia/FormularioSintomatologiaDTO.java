package br.edu.upe.huocbackend.controller.dto.formSintomatologia;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.model.categoriaSintoma.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "FormularioSintomatologiaDTO")
public class FormularioSintomatologiaDTO {
    @NotNull
    public Geral catGeral;
    @NotNull
    public Respiratorio catRespiratorio;
    @NotNull
    public Inflamatorio catInflamatorio;
    @NotNull
    public Cardiovascular catCardiovascular;
    @NotNull
    public Gastrointestinal catGastrointestinal;
    @NotNull
    public Neurologico catNeurologico;
    @NotNull
    public Musculoesqueletico catMusculoesqueletico;
    @NotNull
    public Hematologico catHematologico;
    @NotNull
    public Psiquiatrico catPsiquiatrico;
    @NotNull
    public LocalDateTime data;
    @NotNull
    public Integer numProntuario;
    @NotBlank
    public String observacoes;
    @NotNull
    public Paciente paciente;

    public FormularioSintomatologiaDTO(Geral catGeral, Respiratorio catRespiratorio, Inflamatorio catInflamatorio,
                                    Cardiovascular catCardiovascular, Gastrointestinal catGastrointestinal, Neurologico catNeurologico,
                                    Musculoesqueletico catMusculoesqueletico, Hematologico catHematologico, Psiquiatrico catPsiquiatrico,
                                    LocalDateTime data, Integer numProntuario, String observacoes, Paciente paciente) {
        this.catGeral = catGeral;
        this.catRespiratorio = catRespiratorio;
        this.catInflamatorio = catInflamatorio;
        this.catCardiovascular = catCardiovascular;
        this.catGastrointestinal = catGastrointestinal;
        this.catNeurologico = catNeurologico;
        this.catMusculoesqueletico = catMusculoesqueletico;
        this.catHematologico = catHematologico;
        this.catPsiquiatrico = catPsiquiatrico;
        this.data = data;
        this.numProntuario = numProntuario;
        this.observacoes = observacoes;
        this.paciente = paciente;
    }
}
