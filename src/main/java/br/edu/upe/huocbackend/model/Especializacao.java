package br.edu.upe.huocbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

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

    public Especializacao(String tipoEspecializacao) {
        this.tipoEspecializacao = tipoEspecializacao;
    }
}
