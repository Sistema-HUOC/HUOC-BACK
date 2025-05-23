package br.edu.upe.huocbackend.model.categoriaSintoma;


import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Audited
@Entity
@Table(name = "cat_musculoesquelitico")
public class Musculoesqueletico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean dorArticular;

    @Column(nullable = false)
    private float rigidezMuscular;

    @Column(nullable = false)
    private boolean fraquezaMuscular;

    @Column(nullable = false)
    private boolean articulacaoInchada;

    @Column(nullable = false)
    private String observacao;

    public Musculoesqueletico() {}

    public Musculoesqueletico(boolean dorArticular, float rigidezMuscular, boolean fraquezaMuscular, boolean articulacaoInchada, String observacao) {
        this.dorArticular = dorArticular;
        this.rigidezMuscular = rigidezMuscular;
        this.fraquezaMuscular = fraquezaMuscular;
        this.articulacaoInchada = articulacaoInchada;
        this.observacao = observacao;
    }

    public UUID getId() {return id;}

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
