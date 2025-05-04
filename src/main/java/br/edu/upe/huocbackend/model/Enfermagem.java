package br.edu.upe.huocbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Audited
@Getter
@Setter
@NoArgsConstructor
public class Enfermagem extends User{

    @Column(nullable = false,unique = true)
    private String coren;

    public Enfermagem(String nome, String cpf, String email, String password, AcessLevel level, String coren) {
        super(nome, cpf, email, password, level);
        this.coren = coren;
    }

    @OneToMany(mappedBy = "enfermagem")
    private List<Paciente> pacientes;
}
