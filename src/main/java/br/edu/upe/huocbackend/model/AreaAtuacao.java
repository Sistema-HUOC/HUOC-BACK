package br.edu.upe.huocbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;
import java.util.UUID;

@Entity
@Audited
@Getter
@Setter
@NoArgsConstructor
public class AreaAtuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nomeArea;

    @ManyToMany(mappedBy = "areasAtuacao")
    private List<Pesquisador> pesquisadores;

    public AreaAtuacao(String nomeArea, List<Pesquisador> pesquisadores) {
        this.nomeArea = nomeArea;
        this.pesquisadores = pesquisadores;
    }
}