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
@Table(name = "cat_cardiovascular")
public class Cardiovascular {

    public Cardiovascular(boolean palpitações, float dorToraxica, boolean tontura, boolean pressaoAlta, boolean extremidadesFrias, String observacao) {
        this.palpitações = palpitações;
        this.dorToraxica = dorToraxica;
        this.tontura = tontura;
        this.pressaoAlta = pressaoAlta;
        this.extremidadesFrias = extremidadesFrias;
        this.observacao = observacao;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean palpitações;

    @Column(nullable = false)
    private float dorToraxica;

    @Column(nullable = false)
    private boolean tontura;

    @Column(nullable = false)
    private boolean pressaoAlta;

    @Column(nullable = false)
    private boolean extremidadesFrias;

    @Column(nullable = false)
    private String observacao;

    @OneToOne(mappedBy = "catCardiovascular", cascade = CascadeType.ALL)
    private FormularioSintomatologia formSintomatologia;
}
