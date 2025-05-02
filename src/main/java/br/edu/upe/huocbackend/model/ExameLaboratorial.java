package br.edu.upe.huocbackend.model;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Audited
@Getter
@Setter
@Table(name = "exameLaboratorial")
public class ExameLaboratorial {

    public ExameLaboratorial() {}

    public ExameLaboratorial(String name, String unidade, String referencia, String interpretacao,
                             FormularioMedico idFormularioMedico, CategoriaExame idCategoriaExame,String resultado ) {
        this.name = name;
        this.unidade = unidade;
        this.referencia = referencia;
        this.interpretacao = interpretacao;
        this.formularioMedico = idFormularioMedico;
        this.categoriaExame = idCategoriaExame;
        this.resultado = resultado;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unidade;

    @Column(nullable = false)
    private String referencia;

    @Column(nullable = false)
    private String interpretacao;

    @ManyToOne
    @JoinColumn(name = "idFormularioMedico")
    private FormularioMedico formularioMedico;

    @ManyToOne
    @JoinColumn(name = "idCategoriaExame")
    private CategoriaExame categoriaExame;

    @Column(nullable = false)
    private String resultado;
}
