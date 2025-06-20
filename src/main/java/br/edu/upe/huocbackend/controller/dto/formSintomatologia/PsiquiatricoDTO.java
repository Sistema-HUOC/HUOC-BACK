package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PsiquiatricoDTO {
    private boolean ansiedade;
    private float depressao;
    private boolean alucinacao;
    private boolean insonia;
    private String observacao;
}
