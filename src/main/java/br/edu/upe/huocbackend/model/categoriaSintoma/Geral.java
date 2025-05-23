package br.edu.upe.huocbackend.model.categoriaSintoma;


import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Audited
@Entity
@Table(name = "cat_geral")
public class Geral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean febre;

    @Column(nullable = false)
    private float temperatura;

    @Column(nullable = false)
    private boolean cansaço;

    @Column(nullable = false)
    private boolean sudorese;

    @Column(nullable = false)
    private boolean perdaPeso;

    @Column(nullable = false)
    private boolean edema;

    @Column(nullable = false)
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

    public UUID getId() {return id;}

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
