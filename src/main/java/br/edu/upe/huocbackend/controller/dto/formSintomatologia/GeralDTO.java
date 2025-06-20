package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeralDTO {
    private boolean febre;
    private float temperatura;
    private boolean cansaço;
    private boolean sudorese;
    private boolean perdaPeso;
    private boolean edema;
    private String observacao;
}
