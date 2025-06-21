package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardiovascularDTO {
    private boolean palpitacoes;
    private float dorToraxica;
    private boolean tontura;
    private boolean pressaoAlta;
    private boolean extremidadesFrias;
    private String observacao;
}
