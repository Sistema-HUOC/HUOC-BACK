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
@Table(name = "cat_neurologico")
public class Neurologico {

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

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean dordeCabeça;

    @Column(nullable = false)
    private float confusaoMental;

    @Column(nullable = false)
    private boolean convulsoes;

    @Column(nullable = false)
    private boolean dorNeurotipica;

    @Column(nullable = false)
    private boolean parestesia;

    @Column(nullable = false)
    private boolean paresia;

    @Column(nullable = false)
    private boolean plegia;

    @Column(nullable = false)
    private String observacao;

    @OneToOne(mappedBy = "catNeurologico", cascade = CascadeType.ALL)
    @JsonIgnore
    private FormularioSintomatologia formSintomatologia;
}
