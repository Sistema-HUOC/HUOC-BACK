package br.edu.upe.huocbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Data
@Audited
@Table(name = "Sintoma")
public class Sintoma {

    public Sintoma() {}

    public Sintoma(String nome) {
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_categoriaSintoma", nullable = false)
    private CategoriaSintoma categoriaSintoma;

    @ManyToOne
    @JoinColumn(name = "id_formularioSintomatologia", nullable = false)
    private FormularioSintomatologia formularioSintomatologia;
}
