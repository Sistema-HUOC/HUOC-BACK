package br.com.HUOC_BACK.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Audited
@Table(name = "formSintomatologia")
public class FormularioSintomatologia {

    public FormularioSintomatologia(){}

    public FormularioSintomatologia(LocalDateTime data, int numProntuario, String observacoes, Paciente paciente){
        this.data = data;
        this.numProntuario = numProntuario;
        this.observacoes = observacoes;
        this.paciente = paciente;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private int numProntuario;

    @Column(nullable = false, length = 355)
    private String observacoes;

    @OneToOne(mappedBy = "formularioSintomatologia", cascade = CascadeType.ALL)
    private Paciente paciente;

    @OneToMany(mappedBy = "formularioSintomatologia", cascade = CascadeType.ALL)
    private List<DocumentoExameLaboratorial> documentosExame;

    @OneToMany(mappedBy = "formularioSintomatologia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sintoma> sintomas;
}
