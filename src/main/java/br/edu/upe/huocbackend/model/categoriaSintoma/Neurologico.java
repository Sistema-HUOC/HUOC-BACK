package br.edu.upe.huocbackend.model.categoriaSintoma;

public class Neurologico {

    private boolean dordeCabeça;
    private float confusaoMental;
    private boolean convulsoes;
    private boolean dorNeurotipica;
    private boolean parestesia;
    private boolean paresia;
    private boolean plegia;
    private String observacao;

    public Neurologico() {}

    public Neurologico(boolean dordeCabeça, float confusaoMental, boolean convulsoes, boolean dorNeurotipica, boolean parestesia, boolean paresia, boolean plegia, String observacao) {
        this.dordeCabeça = dordeCabeça;
        this.confusaoMental = confusaoMental;
        this.convulsoes = convulsoes;
        this.dorNeurotipica = dorNeurotipica;
        this.parestesia = parestesia;
        this.paresia = paresia;
        this.plegia = plegia;
        this.observacao = observacao;
    }

    public boolean getDordeCabeça() {return dordeCabeça;}

    public void setDordeCabeça(boolean dordeCabeça) {this.dordeCabeça = dordeCabeça;}

    public float getConfusaoMental() {return confusaoMental;}

    public void setConfusaoMental(float confusaoMental) {this.confusaoMental = confusaoMental;}

    public boolean getConvulsoes() {return convulsoes;}

    public void setConvulsoes(boolean convulsoes) {this.convulsoes = convulsoes;}

    public boolean getDorNeurotipica() {return dorNeurotipica;}

    public void setDorNeurotipica(boolean dorNeurotipica) {this.dorNeurotipica = dorNeurotipica;}

    public boolean getParestesia() {return parestesia;}

    public void setParestesia(boolean parestesia) {this.parestesia = parestesia;}

    public boolean getParesia() {return paresia;}

    public void setParesia(boolean paresia) {this.paresia = paresia;}

    public boolean getPlegia() {return plegia;}

    public void setPlegia(boolean plegia) {this.plegia = plegia;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {
        this.observacao = observacao;}
}
