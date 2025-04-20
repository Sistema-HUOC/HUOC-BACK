package br.com.HUOC_BACK.model;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Audited
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getObservacoesAdicionaisFormularioMedico() {
        return observacoesAdicionaisFormularioMedico;
    }

    public void setObservacoesAdicionaisFormularioMedico(String observacoesAdicionaisFormularioMedico) {
        this.observacoesAdicionaisFormularioMedico = observacoesAdicionaisFormularioMedico;
    }

    public String getId_Medico() {
        return id_Medico;
    }

    public void setId_Medico(String id_Medico) {
        this.id_Medico = id_Medico;
    }
}
