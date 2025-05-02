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
public class Medico extends User{

    @Column(nullable = false,unique = true)
    private String CRM;

    @ManyToOne
    @JoinColumn(name = "idEspecializacao")
    private Especializacao especializacao;

    @OneToMany(mappedBy = "medico")
    private List<FormularioMedico> formularioMedicos;

    public Medico(String nome, String cpf, String email, String password, AcessLevel level, String CRM, Especializacao especializacao) {
        super(nome, cpf, email, password, level);
        this.CRM = CRM;
        this.especializacao = especializacao;
    }
}
