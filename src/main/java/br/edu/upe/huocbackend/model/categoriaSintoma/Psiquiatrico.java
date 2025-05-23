package br.edu.upe.huocbackend.model.categoriaSintoma;

public class Psiquiatrico {

    private boolean ansiedade;
    private float depressao;
    private boolean alucinacao;
    private boolean insonia;
    private String observacao;

    public Psiquiatrico() {}

    public Psiquiatrico(boolean ansiedade, float depressao, boolean alucinacao, boolean insonia, String observacao) {
        this.ansiedade = ansiedade;
        this.depressao = depressao;
        this.alucinacao = alucinacao;
        this.insonia = insonia;
        this.observacao = observacao;
    }

    public boolean getAnsiedade() {
        return ansiedade;
    }

    public void setAnsiedade(boolean ansiedade) {
        this.ansiedade = ansiedade;
    }

    public float getDepressao() {
        return depressao;
    }

    public void setDepressao(float depressao) {
        this.depressao = depressao;
    }

    public boolean getAlucinacao() {
        return alucinacao;
    }

    public void setAlucinacao(boolean alucinacao) {
        this.alucinacao = alucinacao;
    }

    public boolean getInsonia() {
        return insonia;
    }

    public void setInsonia(boolean insonia) {
        this.insonia = insonia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
