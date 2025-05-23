package br.edu.upe.huocbackend.model.categoriaSintoma;


import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Audited
@Entity
@Table(name = "cat_hematologico")
public class Hematologico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean sangramentoAnormal;

    @Column(nullable = false)
    private float hematomasFaceis;

    @Column(nullable = false)
    private boolean palides;

    @Column(nullable = false)
    private String observacao;

    public Hematologico() {}

    public Hematologico(boolean sangramentoAnormal, float hematomasFaceis, boolean palides, String observacao) {
        this.sangramentoAnormal = sangramentoAnormal;
        this.hematomasFaceis = hematomasFaceis;
        this.palides = palides;
        this.observacao = observacao;
    }

    public UUID getId() {return id;}

    public boolean getSangramentoAnormal() {return sangramentoAnormal;}

    public void setSangramentoAnormal(boolean sangramentoAnormal) {this.sangramentoAnormal = sangramentoAnormal;}

    public float getHematomasFaceis() {return hematomasFaceis;}

    public void setHematomasFaceis(float hematomasFaceis) {this.hematomasFaceis = hematomasFaceis;}

    public boolean getPalides() {return palides;}

    public void setPalides(boolean palides) {this.palides = palides;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {this.observacao = observacao;}
}
