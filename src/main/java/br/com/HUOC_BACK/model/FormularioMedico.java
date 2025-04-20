package br.com.HUOC_BACK.model;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Audited
@Getter
@Setter
@Table(name = "formularioMedicos")
public class FormularioMedico {

    public FormularioMedico() {}

    public FormularioMedico(LocalDateTime data, String observacoesAdicionaisFormularioMedico, String id_Medico) {
        this.data = data;
        this.observacoesAdicionaisFormularioMedico = observacoesAdicionaisFormularioMedico;
        this.id_Medico = id_Medico;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private LocalDateTime data;
    @Column(nullable = false)
    private String observacoesAdicionaisFormularioMedico;
    @Column(nullable = false)
    private String id_Medico;
}
