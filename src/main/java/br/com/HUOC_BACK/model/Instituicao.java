package br.com.HUOC_BACK.model;

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
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nomeInstituicao;

    @OneToMany(mappedBy = "instituicao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pesquisador> pesquisadores;

    public Instituicao(String nomeInstituicao, List<Pesquisador> pesquisadores) {
        this.nomeInstituicao = nomeInstituicao;
        this.pesquisadores = pesquisadores;
    }
}