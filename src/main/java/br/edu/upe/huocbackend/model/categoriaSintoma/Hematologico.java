package br.edu.upe.huocbackend.model.categoriaSintoma;

public class Hematologico {

    private boolean sangramentoAnormal;
    private float hematomasFaceis;
    private boolean palides;
    private String observacao;

    public Hematologico() {}

    public Hematologico(boolean sangramentoAnormal, float hematomasFaceis, boolean palides, String observacao) {
        this.sangramentoAnormal = sangramentoAnormal;
        this.hematomasFaceis = hematomasFaceis;
        this.palides = palides;
        this.observacao = observacao;
    }

    public boolean getSangramentoAnormal() {return sangramentoAnormal;}

    public void setSangramentoAnormal(boolean sangramentoAnormal) {this.sangramentoAnormal = sangramentoAnormal;}

    public float getHematomasFaceis() {return hematomasFaceis;}

    public void setHematomasFaceis(float hematomasFaceis) {this.hematomasFaceis = hematomasFaceis;}

    public boolean getPalides() {return palides;}

    public void setPalides(boolean palides) {this.palides = palides;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {this.observacao = observacao;}
}
