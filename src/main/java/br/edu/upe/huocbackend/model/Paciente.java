package br.edu.upe.huocbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Audited
@Table(name = "Paciente")
public class Paciente {

    public Paciente(String nome, String cpf, LocalDate dataNasc, String sexo, String telefone,
                    Boolean htvl1, Boolean htvl2, Integer numProntuario, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.telefone = telefone;
        this.htvl1 = htvl1;
        this.htvl2 = htvl2;
        this.numProntuario = numProntuario;
        this.endereco = endereco;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNasc;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private Boolean htvl1;

    @Column(nullable = false)
    private Boolean htvl2;

    @Column(nullable = false)
    private Integer numProntuario;

    @ManyToOne
    @JoinColumn(name = "idEnfermagem")
    private Enfermagem enfermagem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEndereco", nullable = false)
    private Endereco endereco;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Exame> exames;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<FormularioSintomatologia> formularioSintomatologia;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Prontuario> prontuarios;
}
