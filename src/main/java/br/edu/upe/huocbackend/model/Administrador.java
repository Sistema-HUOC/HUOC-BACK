package br.edu.upe.huocbackend.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Audited
@NoArgsConstructor
public class Administrador extends User{
    public Administrador(String nome, String cpf, String email, String password, AcessLevel level) {
        super(nome, cpf, email, password, level);
    }
}
