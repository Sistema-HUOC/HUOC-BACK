package br.edu.upe.huocbackend.controller.dto.paciente;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PacienteResponse {

    public static class Enfermagem{
        public String nome;
        public String coren;
    }
    public static class Endereco{
        public String rua;
        public String numero;
        public String bairro;
        public String cidade;
        public String estado;
        public String cep;
        public String complemento;
    }
    public static class Exame{
        public LocalDateTime data;
        public String anexo;
        public String tipoExame;
    }
    public static class FormularioSintomatologiaDTO {
        public LocalDateTime data;
        public Integer numProntuario;
        public String observacoes;

        public GeralDTO catGeral;
        public RespiratorioDTO catRespiratorio;
        public InflamatorioDTO catInflamatorio;
        public CardiovascularDTO catCardiovascular;
        public GastrointestinalDTO catGastrointestinal;
        public NeurologicoDTO catNeurologico;
        public MusculoesqueleticoDTO catMusculoesqueletico;
        public HematologicoDTO catHematologico;
        public PsiquiatricoDTO catPsiquiatrico;

        public static class GeralDTO {
            public boolean febre;
            public float temperatura;
            public boolean cansaço;
            public boolean sudorese;
            public boolean perdaPeso;
            public boolean edema;
            public String observacao;
        }

        public static class RespiratorioDTO {
            public boolean tosse;
            public boolean faltadeAr;
            public boolean doraoRespirar;
            public boolean espirro;
            public boolean coriza;
            public String observacao;
        }

        public static class InflamatorioDTO {
            public boolean dordeGarganta;
            public float linfonodosInchados;
            public boolean erupcaoCutanea;
            public boolean ulceraBocal;
            public String observacao;
        }

        public static class CardiovascularDTO {
            public boolean palpitações;
            public float dorToraxica;
            public boolean tontura;
            public boolean pressaoAlta;
            public boolean extremidadesFrias;
            public String observacao;
        }

        public static class GastrointestinalDTO {
            public boolean nausea;
            public float diarreia;
            public boolean prisaodeVentre;
            public boolean dorAbdominal;
            public boolean perdadeApetite;
            public boolean constipação;
            public String observacao;
        }

        public static class NeurologicoDTO {
            public boolean dordeCabeça;
            public float confusaoMental;
            public boolean convulsoes;
            public boolean dorNeurotipica;
            public boolean parestesia;
            public boolean paresia;
            public boolean plegia;
            public String observacao;
        }

        public static class MusculoesqueleticoDTO {
            public boolean dorArticular;
            public float rigidezMuscular;
            public boolean fraquezaMuscular;
            public boolean articulacaoInchada;
            public String observacao;
        }

        public static class HematologicoDTO {
            public boolean sangramentoAnormal;
            public float hematomasFaceis;
            public boolean palides;
            public String observacao;
        }

        public static class PsiquiatricoDTO {
            public boolean ansiedade;
            public float depressao;
            public boolean alucinacao;
            public boolean insonia;
            public String observacao;
        }
    }
    public String nome;
    public String cpf;
    public LocalDate dataNasc;
    public String sexo;
    public String telefone;
    public Boolean htvl1;
    public Boolean htvl2;
    public Integer numProntuario;
    public Enfermagem enfermagem;
    public Endereco endereco;
    public List<Exame> exames;
    public List<FormularioSintomatologiaDTO> formularioSintomatologia;
}
