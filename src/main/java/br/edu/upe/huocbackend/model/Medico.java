package br.edu.upe.huocbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

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

    public Medico(String nome, String cpf, String email, String password, AcessLevel level, String CRM, Especializacao especializacao) {
        super(nome, cpf, email, password, level);
        this.CRM = CRM;
        this.especializacao = especializacao;
    }
}
