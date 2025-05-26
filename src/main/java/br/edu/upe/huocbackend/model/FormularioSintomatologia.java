package br.edu.upe.huocbackend.model;

import br.edu.upe.huocbackend.model.categoriaSintoma.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited
@Table(name = "formSintomatologia")
public class FormularioSintomatologia {

    public FormularioSintomatologia(Geral catGeral, Respiratorio catRespiratorio, Inflamatorio catInflamatorio,
                                    Cardiovascular catCardiovascular, Gastrointestinal catGastrointestinal, Neurologico catNeurologico,
                                    Musculoesqueletico catMusculoesqueletico, Hematologico catHematologico, Psiquiatrico catPsiquiatrico,
                                    LocalDateTime data, Integer numProntuario, String observacoes) {
        this.catGeral = catGeral;
        this.catRespiratorio = catRespiratorio;
        this.catInflamatorio = catInflamatorio;
        this.catCardiovascular = catCardiovascular;
        this.catGastrointestinal = catGastrointestinal;
        this.catNeurologico = catNeurologico;
        this.catMusculoesqueletico = catMusculoesqueletico;
        this.catHematologico = catHematologico;
        this.catPsiquiatrico = catPsiquiatrico;
        this.data = data;
        this.numProntuario = numProntuario;
        this.observacoes = observacoes;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private Integer numProntuario;

    @Column(nullable = false, length = 355)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idGeral", nullable = false)
    private Geral catGeral;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRespiratorio", nullable = false)
    private Respiratorio catRespiratorio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idInflamatorio", nullable = false)
    private Inflamatorio catInflamatorio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCardiovascular", nullable = false)
    private Cardiovascular catCardiovascular;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idGastrointestinal", nullable = false)
    private Gastrointestinal catGastrointestinal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idNeurologico", nullable = false)
    private Neurologico catNeurologico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMusculoesqueletico", nullable = false)
    private Musculoesqueletico catMusculoesqueletico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idHematologico", nullable = false)
    private Hematologico catHematologico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPsiquiatrico", nullable = false)
    private Psiquiatrico catPsiquiatrico;
}
