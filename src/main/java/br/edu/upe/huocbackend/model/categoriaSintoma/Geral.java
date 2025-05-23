package br.edu.upe.huocbackend.model.categoriaSintoma;

public class Geral {

    private boolean febre;
    private float temperatura;
    private boolean cansaço;
    private boolean sudorese;
    private boolean perdaPeso;
    private boolean edema;
    private String observacao;

    public Geral() {}

    public Geral(boolean febre, float temperatura, boolean cansaço, boolean sudorese, boolean perdaPeso, boolean edema, String observacao) {
        this.febre = febre;
        this.temperatura = temperatura;
        this.cansaço = cansaço;
        this.sudorese = sudorese;
        this.perdaPeso = perdaPeso;
        this.edema = edema;
        this.observacao = observacao;
    }

    public boolean getFebre() {return febre;}

    public void setFebre(boolean febre) {this.febre = febre;}

    public float getTemperatura() {return temperatura;}

    public void setTemperatura(float temperatura) {this.temperatura = temperatura;}

    public boolean getCansaço() {return cansaço;}

    public void setCansaço(boolean cansaço) {this.cansaço = cansaço;}

    public boolean getSudorese() {return sudorese;}

    public void setSudorese(boolean sudorese) {this.sudorese = sudorese;}

    public boolean getPerdaPeso() {return perdaPeso;}

    public void setPerdaPeso(boolean perdaPeso) {this.perdaPeso = perdaPeso;}

    public boolean getEdema() {return edema;}

    public void setEdema(boolean edema) {this.edema = edema;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {this.observacao = observacao;}
}
