package br.edu.upe.huocbackend.model.categoriaSintoma;

import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Audited
@Table(name = "cat_gastrointestinal")
public class Gastrointestinal {

    public Gastrointestinal(boolean nausea, float diarreia, boolean prisaodeVentre, boolean dorAbdominal, boolean perdadeApetite, boolean constipação, String observacao) {
        this.nausea = nausea;
        this.diarreia = diarreia;
        this.prisaodeVentre = prisaodeVentre;
        this.dorAbdominal = dorAbdominal;
        this.perdadeApetite = perdadeApetite;
        this.constipação = constipação;
        this.observacao = observacao;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "catGastrointestinal", cascade = CascadeType.ALL)
    @JsonIgnore
    private FormularioSintomatologia formSintomatologia;
}
