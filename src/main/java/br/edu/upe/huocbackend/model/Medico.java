package br.edu.upe.huocbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Audited
@NoArgsConstructor
public class Medico extends User {

    @Column(nullable = false, unique = true)
    private String crm;

    @ManyToMany
    @JoinTable(
            name = "medico_especializacoes",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especializacoes_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"medico_id", "especializacoes_id"})
    )
    private Set<Especializacao> especializacoes = new HashSet<>();


    @OneToMany(mappedBy = "medico")
    private List<FormularioMedico> formulariosMedicos;

    public Medico(String nome, String cpf, String email, String password, AcessLevel level, String crm, Set<Especializacao> especializacoes) {
        super(nome, cpf, email, password, level);
        this.crm = crm;
        this.especializacoes = especializacoes;
    }

    public Set<String> getSetEspecializacaos(){
        return this.especializacoes.stream().map(Especializacao::getTipoEspecializacao).collect(Collectors.toSet());
    }
}
