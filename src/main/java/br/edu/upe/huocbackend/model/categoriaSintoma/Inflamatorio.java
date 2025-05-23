package br.edu.upe.huocbackend.model.categoriaSintoma;


import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Audited
@Entity
@Table(name = "cat_inflamatorio")
public class Inflamatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean dordeGarganta;

    @Column(nullable = false)
    private float linfonodosInchados;

    @Column(nullable = false)
    private boolean erupcaoCutanea;

    @Column(nullable = false)
    private boolean ulceraBocal;

    @Column(nullable = false)
    private String observacao;

    public Inflamatorio() {}

    public Inflamatorio(boolean dordeGarganta, float linfonodosInchados, boolean erupcaoCutanea, boolean ulceraBocal, String observacao) {
        this.dordeGarganta = dordeGarganta;
        this.linfonodosInchados = linfonodosInchados;
        this.erupcaoCutanea = erupcaoCutanea;
        this.ulceraBocal = ulceraBocal;
        this.observacao = observacao;
    }

    public UUID getId() {return id;}

    public boolean getDordeGarganta() {return dordeGarganta;}

    public void setDordeGarganta(boolean dordeGarganta) {this.dordeGarganta = dordeGarganta;}

    public float getLinfonodosInchados() {return linfonodosInchados;}

    public void setLinfonodosInchados(float linfonodosInchados) {this.linfonodosInchados = linfonodosInchados;}

    public boolean getErupcaoCutanea() {return erupcaoCutanea;}

    public void setErupcaoCutanea(boolean erupcaoCutanea) {this.erupcaoCutanea = erupcaoCutanea;}

    public boolean getUlceraBocal() {return ulceraBocal;}

    public void setUlceraBocal(boolean ulceraBocal) {this.ulceraBocal = ulceraBocal;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {this.observacao = observacao;}
}
