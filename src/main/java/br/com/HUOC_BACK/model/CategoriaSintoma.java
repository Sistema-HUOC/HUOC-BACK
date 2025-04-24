package br.com.HUOC_BACK.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Data
@Audited
@Table(name = "catSintoma")
public class CategoriaSintoma {

    public CategoriaSintoma() {}

    public CategoriaSintoma(String nome, String observacoes) {
        this.nome = nome;
        this.observacoes = observacoes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 350)
    private String observacoes;

}
