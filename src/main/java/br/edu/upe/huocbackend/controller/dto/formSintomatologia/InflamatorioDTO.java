package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InflamatorioDTO {
    private boolean dordeGarganta;
    private float linfonodosInchados;
    private boolean erupcaoCutanea;
    private boolean ulceraBocal;
    private String observacao;
}
