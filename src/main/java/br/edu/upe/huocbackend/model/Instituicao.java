package br.edu.upe.huocbackend.model;

import br.edu.upe.huocbackend.controller.dto.instituicao.CreateInstituicaoDto;
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

    @Column(nullable = false)
    private String nomeInstituicao;

    @Column(nullable = false,unique = true)
    private String nomeCampos;

    @Column(nullable = false)
    private String cnpj;

    @OneToMany(mappedBy = "instituicao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pesquisador> pesquisadores;


    public Instituicao(CreateInstituicaoDto dto) {
        this.nomeInstituicao = dto.nomeInstituicao();
        this.nomeCampos = dto.nomeDoCampos();
        this.cnpj = dto.cnpj();
    }
}