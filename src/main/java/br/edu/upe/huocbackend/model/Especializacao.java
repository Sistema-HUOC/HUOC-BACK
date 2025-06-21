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
public class Especializacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String tipoEspecializacao;

    @ManyToMany(mappedBy = "especializacoes")
    private List<Medico> medicos;

    public Especializacao(String tipoEspecializacao) {
        this.tipoEspecializacao = tipoEspecializacao;
    }
}
