package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MusculoesqueleticoDTO {
    private boolean dorArticular;
    private float rigidezMuscular;
    private boolean fraquezaMuscular;
    private boolean articulacaoInchada;
    private String observacao;
}
