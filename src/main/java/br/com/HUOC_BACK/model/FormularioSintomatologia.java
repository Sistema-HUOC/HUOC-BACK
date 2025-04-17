package br.com.HUOC_BACK.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Audited
@Table(name = "formSintomatologia")
public class FormularioSintomatologia {

    public FormularioSintomatologia(){}

    public FormularioSintomatologia(LocalDateTime data, int numProntuario, String observacoes, String id_Paciente){
        this.data = data;
        this.numProntuario = numProntuario;
        this.observacoes = observacoes;
        this.id_Paciente = id_Paciente;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private int numProntuario;

    @Column(nullable = false, length = 355)
    private String observacoes;

    private String id_Paciente;

}
