package br.edu.upe.huocbackend.model.categoriaSintoma;

public class Cardiovascular {

    private boolean palpitações;
    private float dorToraxica;
    private boolean tontura;
    private boolean pressaoAlta;
    private boolean extremidadesFrias;
    private String observacao;

    public Cardiovascular() {}

    public Cardiovascular(boolean palpitações, float dorToraxica, boolean tontura, boolean pressaoAlta, boolean extremidadesFrias, String observacao) {
        this.palpitações = palpitações;
        this.dorToraxica = dorToraxica;
        this.tontura = tontura;
        this.pressaoAlta = pressaoAlta;
        this.extremidadesFrias = extremidadesFrias;
        this.observacao = observacao;
    }

    public boolean getPalpitações() {return palpitações;}

    public void setPalpitações(boolean palpitações) {this.palpitações = palpitações;}

    public float getDorToraxica() {return dorToraxica;}

    public void setDorToraxica(float dorToraxica) {this.dorToraxica = dorToraxica;}

    public boolean getTontura() {return tontura;}

    public void setTontura(boolean tontura) {this.tontura = tontura;}

    public boolean getPressaoAlta() {return pressaoAlta;}

    public void setPressaoAlta(boolean pressaoAlta) {this.pressaoAlta = pressaoAlta;}

    public boolean getExtremidadesFrias() {return extremidadesFrias;}

    public void setExtremidadesFrias(boolean extremidadesFrias) {this.extremidadesFrias = extremidadesFrias;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {this.observacao = observacao;}
}
