package br.edu.upe.huocbackend.model.categoriaSintoma;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Audited
@Entity
@Table(name = "cat_gastrointestinal")
public class Gastrointestinal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean nausea;

    @Column(nullable = false)
    private float diarreia;

    @Column(nullable = false)
    private boolean prisaodeVentre;

    @Column(nullable = false)
    private boolean dorAbdominal;

    @Column(nullable = false)
    private boolean perdadeApetite;

    @Column(nullable = false)
    private boolean constipação;

    @Column(nullable = false)
    private String observacao;

    public Gastrointestinal() {}

    public Gastrointestinal(boolean nausea, float diarreia, boolean prisaodeVentre, boolean dorAbdominal, boolean perdadeApetite, boolean constipação, String observacao) {
        this.nausea = nausea;
        this.diarreia = diarreia;
        this.prisaodeVentre = prisaodeVentre;
        this.dorAbdominal = dorAbdominal;
        this.perdadeApetite = perdadeApetite;
        this.constipação = constipação;
        this.observacao = observacao;
    }

    public UUID getId() {return id;}

    public boolean getNausea() {return nausea;}

    public void setNausea(boolean nausea) {this.nausea = nausea;}

    public float getDiarreia() {return diarreia;}

    public void setDiarreia(float diarreia) {this.diarreia = diarreia;}

    public boolean getPrisaodeVentre() {return prisaodeVentre;}

    public void setPrisaodeVentre(boolean prisaodeVentre) {this.prisaodeVentre = prisaodeVentre;}

    public boolean getDorAbdominal() {return dorAbdominal;}

    public void setDorAbdominal(boolean dorAbdominal) {this.dorAbdominal = dorAbdominal;}

    public boolean getPerdadeApetite() {return perdadeApetite;}

    public void setPerdadeApetite(boolean perdadeApetite) {this.perdadeApetite = perdadeApetite;}

    public boolean getConstipação() {return constipação;}

    public void setConstipação(boolean constipação) {this.constipação = constipação;}

    public String getObservacao() {return observacao;}

    public void setObservacao(String observacao) {this.observacao = observacao;}
}
