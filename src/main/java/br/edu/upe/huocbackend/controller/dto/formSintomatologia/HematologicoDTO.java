package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HematologicoDTO {
    private boolean sangramentoAnormal;
    private boolean hematomasFaceis;
    private boolean palides;
    private String observacao;
}
