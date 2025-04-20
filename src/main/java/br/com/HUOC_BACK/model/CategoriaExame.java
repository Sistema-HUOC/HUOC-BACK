package br.com.HUOC_BACK.model;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import java.util.UUID;

@Entity
@Audited
@Table(name = "categoriaExames")
public class CategoriaExame {

    public CategoriaExame() {}

    public CategoriaExame(String nomeCategoriaExame) {
        this.nomeCategoriaExame = nomeCategoriaExame;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String nomeCategoriaExame;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCategoriaExame() {
        return nomeCategoriaExame;
    }

    public void setNomeCategoriaExame(String nomeCategoriaExame) {
        this.nomeCategoriaExame = nomeCategoriaExame;
    }
}
