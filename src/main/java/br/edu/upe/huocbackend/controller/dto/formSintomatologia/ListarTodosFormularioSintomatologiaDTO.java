package br.edu.upe.huocbackend.controller.dto.formSintomatologia;

import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ListarTodosFormularioSintomatologiaDTO {

    private UUID id;
    private LocalDateTime data;
    private Integer numProntuario;
    private String observacoes;
    private UUID idPaciente;
    private String nomePaciente;

    private GeralDTO catGeral;
    private RespiratorioDTO catRespiratorio;
    private InflamatorioDTO catInflamatorio;
    private CardiovascularDTO catCardiovascular;
    private GastrointestinalDTO catGastrointestinal;
    private NeurologicoDTO catNeurologico;
    private MusculoesqueleticoDTO catMusculoesqueletico;
    private HematologicoDTO catHematologico;
    private PsiquiatricoDTO catPsiquiatrico;

    public ListarTodosFormularioSintomatologiaDTO(FormularioSintomatologia form) {
        this.id = form.getId();
        this.data = form.getData();
        this.numProntuario = form.getNumProntuario();
        this.observacoes = form.getObservacoes();

        this.idPaciente = form.getPaciente().getId();
        this.nomePaciente = form.getPaciente().getNome();

        this.catGeral = new GeralDTO(
                form.getCatGeral().isFebre(),
                form.getCatGeral().getTemperatura(),
                form.getCatGeral().isCansaço(),
                form.getCatGeral().isSudorese(),
                form.getCatGeral().isPerdaPeso(),
                form.getCatGeral().isEdema(),
                form.getCatGeral().getObservacao()
        );

        this.catRespiratorio = new RespiratorioDTO(
                form.getCatRespiratorio().isTosse(),
                form.getCatRespiratorio().isFaltadeAr(),
                form.getCatRespiratorio().isDoraoRespirar(),
                form.getCatRespiratorio().isEspirro(),
                form.getCatRespiratorio().isCoriza(),
                form.getCatRespiratorio().getObservacao()
        );

        this.catInflamatorio = new InflamatorioDTO(
                form.getCatInflamatorio().isDordeGarganta(),
                form.getCatInflamatorio().getLinfonodosInchados(),
                form.getCatInflamatorio().isErupcaoCutanea(),
                form.getCatInflamatorio().isUlceraBocal(),
                form.getCatInflamatorio().getObservacao()
        );

        this.catCardiovascular = new CardiovascularDTO(
                form.getCatCardiovascular().isPalpitações(),
                form.getCatCardiovascular().getDorToraxica(),
                form.getCatCardiovascular().isTontura(),
                form.getCatCardiovascular().isPressaoAlta(),
                form.getCatCardiovascular().isExtremidadesFrias(),
                form.getCatCardiovascular().getObservacao()
        );

        this.catGastrointestinal = new GastrointestinalDTO(
                form.getCatGastrointestinal().isNausea(),
                form.getCatGastrointestinal().getDiarreia(),
                form.getCatGastrointestinal().isPrisaodeVentre(),
                form.getCatGastrointestinal().isDorAbdominal(),
                form.getCatGastrointestinal().isPerdadeApetite(),
                form.getCatGastrointestinal().isConstipação(),
                form.getCatGastrointestinal().getObservacao()
        );

        this.catNeurologico = new NeurologicoDTO(
                form.getCatNeurologico().isDordeCabeça(),
                form.getCatNeurologico().getConfusaoMental(),
                form.getCatNeurologico().isConvulsoes(),
                form.getCatNeurologico().isDorNeurotipica(),
                form.getCatNeurologico().isParestesia(),
                form.getCatNeurologico().isParesia(),
                form.getCatNeurologico().isPlegia(),
                form.getCatNeurologico().getObservacao()
        );

        this.catMusculoesqueletico = new MusculoesqueleticoDTO(
                form.getCatMusculoesqueletico().isDorArticular(),
                form.getCatMusculoesqueletico().getRigidezMuscular(),
                form.getCatMusculoesqueletico().isFraquezaMuscular(),
                form.getCatMusculoesqueletico().isArticulacaoInchada(),
                form.getCatMusculoesqueletico().getObservacao()
        );

        this.catHematologico = new HematologicoDTO(
                form.getCatHematologico().isSangramentoAnormal(),
                form.getCatHematologico().getHematomasFaceis(),
                form.getCatHematologico().isPalides(),
                form.getCatHematologico().getObservacao()
        );

        this.catPsiquiatrico = new PsiquiatricoDTO(
                form.getCatPsiquiatrico().isAnsiedade(),
                form.getCatPsiquiatrico().getDepressao(),
                form.getCatPsiquiatrico().isAlucinacao(),
                form.getCatPsiquiatrico().isInsonia(),
                form.getCatPsiquiatrico().getObservacao()
        );
    }
}
