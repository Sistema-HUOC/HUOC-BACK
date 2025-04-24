package br.com.HUOC_BACK.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Audited
@Table(name = "Paciente")
public class Paciente {

    public Paciente(String nome, String cpf, Date dataNasc, String sexo, String numero,
                    String htvl1, String htvl2, Integer numProntuario, Endereco endereco,
                    String idEnfermagem) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.numero = numero;
        this.htvl1 = htvl1;
        this.htvl2 = htvl2;
        this.numProntuario = numProntuario;
        this.endereco = endereco;
        this.idEnfermagem = idEnfermagem;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private Date dataNasc;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String htvl1;

    @Column(nullable = false)
    private String htvl2;

    @Column(nullable = false)
    private Integer numProntuario;

    @Column(nullable = false)
    private String idEnfermagem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEndereco", nullable = false)
    private Endereco endereco;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Exame> exames;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFormularioSintomatologia", nullable = false)
    private FormularioSintomatologia formularioSintomatologia;
}
