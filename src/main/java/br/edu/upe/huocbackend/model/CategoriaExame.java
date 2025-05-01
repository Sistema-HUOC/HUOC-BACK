package br.edu.upe.huocbackend.model;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Audited
@Getter
@Setter
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

    @OneToMany(mappedBy = "CategoriaExame")
    private List<ExameLaboratorial> exames;

}
