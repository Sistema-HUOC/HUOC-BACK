package br.edu.upe.huocbackend.model;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import java.time.LocalDateTime;
import java.util.List;
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

    public FormularioMedico(LocalDateTime data, String observacoesAdicionaisFormularioMedico, Medico medico) {
        this.data = data;
        this.observacoesAdicionaisFormularioMedico = observacoesAdicionaisFormularioMedico;
        this.medico = medico;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String observacoesAdicionaisFormularioMedico;

    @ManyToOne
    @JoinColumn(name = "idMedico")
    private Medico medico;

    @OneToMany(mappedBy = "formularioMedico")
    private List<DocumentoResultadoLaboratorial> documentoResultadoLaboratoriais;

    @OneToMany(mappedBy = "formularioMedico")
    private List<ExameLaboratorial> exameLaboratoriais;

    @OneToMany(mappedBy = "formularioMedico")
    private List<Prontuario> prontuarios;
}
