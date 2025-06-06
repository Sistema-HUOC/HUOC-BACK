package br.edu.upe.huocbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Getter
@Setter
@Audited
@NoArgsConstructor
public class Pesquisador extends User {

    @ManyToOne
    @JoinColumn(name = "idInstituicao")
    private Instituicao instituicao;

    @ManyToMany
    @JoinTable(
            name = "pesquisador_area_atuacao",
            joinColumns = @JoinColumn(name = "pesquisador_id"),
            inverseJoinColumns = @JoinColumn(name = "area_atuacao_id")
    )
    private List<AreaAtuacao> areasAtuacao;

    public Pesquisador(String nome, String cpf, String email, String password, AcessLevel level, Instituicao instituicao, List<AreaAtuacao> areasAtuacao) {
        super(nome, cpf, email, password, level);
        this.instituicao = instituicao;
        this.areasAtuacao = areasAtuacao;
    }

    @OneToMany(mappedBy = "pesquisador", cascade = CascadeType.ALL)
    private List<Prontuario> prontuarios;
}
