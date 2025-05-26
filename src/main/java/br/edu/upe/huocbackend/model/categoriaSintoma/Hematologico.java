package br.edu.upe.huocbackend.model.categoriaSintoma;


import br.edu.upe.huocbackend.model.FormularioSintomatologia;
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
@Table(name = "cat_hematologico")
public class Hematologico {

    public Hematologico(boolean sangramentoAnormal, float hematomasFaceis, boolean palides, String observacao) {
        this.sangramentoAnormal = sangramentoAnormal;
        this.hematomasFaceis = hematomasFaceis;
        this.palides = palides;
        this.observacao = observacao;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean sangramentoAnormal;

    @Column(nullable = false)
    private float hematomasFaceis;

    @Column(nullable = false)
    private boolean palides;

    @Column(nullable = false)
    private String observacao;

    @OneToOne(mappedBy = "catHematologico", cascade = CascadeType.ALL)
    private FormularioSintomatologia formSintomatologia;
}
