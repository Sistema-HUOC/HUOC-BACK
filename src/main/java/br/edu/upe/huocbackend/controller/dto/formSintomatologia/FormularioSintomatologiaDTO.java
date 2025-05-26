package br.edu.upe.huocbackend.controller.dto.formSintomatologia;
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
    private Geral catGeral;
    @NotNull
    private Respiratorio catRespiratorio;
    @NotNull
    private Inflamatorio catInflamatorio;
    @NotNull
    private Cardiovascular catCardiovascular;
    @NotNull
    private Gastrointestinal catGastrointestinal;
    @NotNull
    private Neurologico catNeurologico;
    @NotNull
    private Musculoesqueletico catMusculoesqueletico;
    @NotNull
    private Hematologico catHematologico;
    @NotNull
    private Psiquiatrico catPsiquiatrico;
    @NotNull
    private LocalDateTime data;
    @NotNull
    private Integer numProntuario;
    @NotBlank
    private String observacoes;

    public FormularioSintomatologiaDTO(Geral catGeral, Respiratorio catRespiratorio, Inflamatorio catInflamatorio,
                                    Cardiovascular catCardiovascular, Gastrointestinal catGastrointestinal, Neurologico catNeurologico,
                                    Musculoesqueletico catMusculoesqueletico, Hematologico catHematologico, Psiquiatrico catPsiquiatrico,
                                    LocalDateTime data, Integer numProntuario, String observacoes) {
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
    }
}
