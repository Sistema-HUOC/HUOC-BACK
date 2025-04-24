package br.com.HUOC_BACK.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Audited
@Table(name = "Exame")
public class Exame {

    public Exame(LocalDateTime data, String anexo, String tipoExame, Paciente paciente) {
        this.data = data;
        this.anexo = anexo;
        this.tipoExame = tipoExame;
        this.paciente = paciente;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String anexo;

    @Column(nullable = false)
    private String tipoExame;

    @ManyToOne
    @JoinColumn(name = "idPaciente", nullable = false)
    private Paciente paciente;
}
