package br.com.HUOC_BACK.model;
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
                             String id_FormularioMedico, String id_CategoriaExame,String resultado ) {
        this.name = name;
        this.unidade = unidade;
        this.referencia = referencia;
        this.interpretacao = interpretacao;
        this.id_FormularioMedico = id_FormularioMedico;
        this.id_CategoriaExame = id_CategoriaExame;
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
    @Column(nullable = false)
    private String id_FormularioMedico;
    @Column(nullable = false)
    private String id_CategoriaExame;
    @Column(nullable = false)
    private String resultado;
}
