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
@Table(name = "cat_geral")
public class Geral {

    public Geral(boolean febre, float temperatura, boolean cansaço, boolean sudorese, boolean perdaPeso, boolean edema, String observacao) {
        this.febre = febre;
        this.temperatura = temperatura;
        this.cansaço = cansaço;
        this.sudorese = sudorese;
        this.perdaPeso = perdaPeso;
        this.edema = edema;
        this.observacao = observacao;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean febre;

    @Column(nullable = false)
    private float temperatura;

    @Column(nullable = false)
    private boolean cansaço;

    @Column(nullable = false)
    private boolean sudorese;

    @Column(nullable = false)
    private boolean perdaPeso;

    @Column(nullable = false)
    private boolean edema;

    @Column(nullable = false)
    private String observacao;

    @OneToOne(mappedBy = "catGeral", cascade = CascadeType.ALL)
    private FormularioSintomatologia formSintomatologia;
}
