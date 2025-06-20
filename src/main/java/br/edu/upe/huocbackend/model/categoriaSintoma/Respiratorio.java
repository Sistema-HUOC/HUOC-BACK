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
@Table(name = "cat_respiratorio")
public class Respiratorio {

    public Respiratorio(boolean tosse, boolean faltadeAr, boolean doraoRespirar, boolean espirro, boolean coriza, String observacao) {
        this.tosse = tosse;
        this.faltadeAr = faltadeAr;
        this.doraoRespirar = doraoRespirar;
        this.espirro = espirro;
        this.coriza = coriza;
        this.observacao = observacao;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean tosse;

    @Column(nullable = false)
    private boolean faltadeAr;

    @Column(nullable = false)
    private boolean doraoRespirar;

    @Column(nullable = false)
    private boolean espirro;

    @Column(nullable = false)
    private boolean coriza;

    @Column(nullable = false)
    private String observacao;

    @OneToOne(mappedBy = "catRespiratorio", cascade = CascadeType.ALL)
    @JsonIgnore
    private FormularioSintomatologia formSintomatologia;
}
