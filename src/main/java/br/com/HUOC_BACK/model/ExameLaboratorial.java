package br.com.HUOC_BACK.model;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import java.util.UUID;

@Entity
@Audited
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getInterpretacao() {
        return interpretacao;
    }

    public void setInterpretacao(String interpretacao) {
        this.interpretacao = interpretacao;
    }

    public String getId_FormularioMedico() {
        return id_FormularioMedico;
    }

    public void setId_FormularioMedico(String id_FormularioMedico) {
        this.id_FormularioMedico = id_FormularioMedico;
    }

    public String getId_CategoriaExame() {
        return id_CategoriaExame;
    }

    public void setId_CategoriaExame(String id_CategoriaExame) {
        this.id_CategoriaExame = id_CategoriaExame;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
