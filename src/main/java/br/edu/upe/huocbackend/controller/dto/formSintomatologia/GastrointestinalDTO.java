package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GastrointestinalDTO {
    private boolean nausea;
    private float diarreia;
    private boolean prisaodeVentre;
    private boolean dorAbdominal;
    private boolean perdadeApetite;
    private boolean constipação;
    private String observacao;
}
