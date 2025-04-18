package br.com.HUOC_BACK.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.cglib.core.Local;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Audited
@Table(name = "docExameLaboratorial")
public class DocumentoExameLaboratorial {

    public DocumentoExameLaboratorial() {}

    public DocumentoExameLaboratorial(String nome, byte[] imagem, LocalDateTime data) {
        this.nome = nome;
        this.imagem = imagem;
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Lob
    @Column(nullable = false)
    private byte[] imagem;

    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_formSintomatologia", nullable = false)
    private FormularioSintomatologia formularioSintomatologia;
}
