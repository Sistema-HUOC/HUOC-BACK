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
@Table(name = "cat_musculoesquelitico")
public class Musculoesqueletico {

    public Musculoesqueletico(boolean dorArticular, float rigidezMuscular, boolean fraquezaMuscular, boolean articulacaoInchada, String observacao) {
        this.dorArticular = dorArticular;
        this.rigidezMuscular = rigidezMuscular;
        this.fraquezaMuscular = fraquezaMuscular;
        this.articulacaoInchada = articulacaoInchada;
        this.observacao = observacao;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "catMusculoesqueletico", cascade = CascadeType.ALL)
    @JsonIgnore
    private FormularioSintomatologia formSintomatologia;
}
