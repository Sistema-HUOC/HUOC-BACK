package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NeurologicoDTO {
    private boolean dordeCabeca;
    private float confusaoMental;
    private boolean convulsoes;
    private boolean dorNeurotipica;
    private boolean parestesia;
    private boolean paresia;
    private boolean plegia;
    private String observacao;
}
