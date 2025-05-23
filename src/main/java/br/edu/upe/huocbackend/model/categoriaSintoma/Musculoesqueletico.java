package br.edu.upe.huocbackend.model.categoriaSintoma;

public class Musculoesqueletico {

    private boolean dorArticular;
    private float rigidezMuscular;
    private boolean fraquezaMuscular;
    private boolean articulacaoInchada;
    private String observacao;

    public Musculoesqueletico() {}

    public Musculoesqueletico(boolean dorArticular, float rigidezMuscular, boolean fraquezaMuscular, boolean articulacaoInchada, String observacao) {
        this.dorArticular = dorArticular;
        this.rigidezMuscular = rigidezMuscular;
        this.fraquezaMuscular = fraquezaMuscular;
        this.articulacaoInchada = articulacaoInchada;
        this.observacao = observacao;
    }

    public boolean getDorArticular() {return dorArticular;}

    public void setDorArticular(boolean dorArticular) {this.dorArticular = dorArticular;}

    public float getRigidezMuscular() {return rigidezMuscular;}

    public void setRigidezMuscular(float rigidezMuscular) {this.rigidezMuscular = rigidezMuscular;}

    public boolean getFraquezaMuscular() {return fraquezaMuscular;}

    public void setFraquezaMuscular(boolean fraquezaMuscular) {this.fraquezaMuscular = fraquezaMuscular;}

    public boolean getArticulacaoInchada() {return articulacaoInchada;}

    public void setArticulacaoInchada(boolean articulacaoInchada) {this.articulacaoInchada = articulacaoInchada;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {this.observacao = observacao;}
}
