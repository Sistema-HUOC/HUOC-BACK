package br.edu.upe.huocbackend.model.categoriaSintoma;

public class Respiratorio {

    private boolean tosse;
    private boolean faltadeAr;
    private boolean doraoRespirar;
    private boolean espirro;
    private boolean coriza;
    private String observacao;

    public Respiratorio() {}

    public Respiratorio(boolean tosse, boolean faltadeAr, boolean doraoRespirar, boolean espirro, boolean coriza, String observacao) {
        this.tosse = tosse;
        this.faltadeAr = faltadeAr;
        this.doraoRespirar = doraoRespirar;
        this.espirro = espirro;
        this.coriza = coriza;
        this.observacao = observacao;
    }

    public boolean getTosse() {return tosse;}

    public void setTosse(boolean tosse) {this.tosse = tosse;}

    public boolean getFaltadeAr() {return faltadeAr;}

    public void setFaltadeAr(boolean faltadeAr) {this.faltadeAr = faltadeAr;}

    public boolean getDoraoRespirar() {return doraoRespirar;}

    public void setDoraoRespirar(boolean doraoRespirar) {this.doraoRespirar = doraoRespirar;}

    public boolean getEspirro() {return espirro;}

    public void setEspirro(boolean espirro) {this.espirro = espirro;}

    public boolean getCoriza() {return coriza;}

    public void setCoriza(boolean coriza) {this.coriza = coriza;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {this.observacao = observacao;}
}
