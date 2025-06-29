package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RespiratorioDTO {
    private boolean tosse;
    private boolean faltadeAr;
    private boolean doraoRespirar;
    private boolean espirro;
    private boolean coriza;
    private String observacao;
}
