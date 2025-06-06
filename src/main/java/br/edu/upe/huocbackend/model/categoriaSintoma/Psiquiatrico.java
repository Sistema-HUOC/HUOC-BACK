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
@Table(name = "cat_psiquatrico")
public class Psiquiatrico {

    public Psiquiatrico(boolean ansiedade, float depressao, boolean alucinacao, boolean insonia, String observacao) {
        this.ansiedade = ansiedade;
        this.depressao = depressao;
        this.alucinacao = alucinacao;
        this.insonia = insonia;
        this.observacao = observacao;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean ansiedade;

    @Column(nullable = false)
    private float depressao;

    @Column(nullable = false)
    private boolean alucinacao;

    @Column(nullable = false)
    private boolean insonia;

    @Column(nullable = false)
    private String observacao;

    @OneToOne(mappedBy = "catPsiquiatrico", cascade = CascadeType.ALL)
    private FormularioSintomatologia formSintomatologia;
}
