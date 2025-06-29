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
@Table(name = "cat_inflamatorio")
public class Inflamatorio {

    public Inflamatorio(boolean dordeGarganta, float linfonodosInchados, boolean erupcaoCutanea, boolean ulceraBocal, String observacao) {
        this.dordeGarganta = dordeGarganta;
        this.linfonodosInchados = linfonodosInchados;
        this.erupcaoCutanea = erupcaoCutanea;
        this.ulceraBocal = ulceraBocal;
        this.observacao = observacao;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "catInflamatorio", cascade = CascadeType.ALL)
    @JsonIgnore
    private FormularioSintomatologia formSintomatologia;
}
