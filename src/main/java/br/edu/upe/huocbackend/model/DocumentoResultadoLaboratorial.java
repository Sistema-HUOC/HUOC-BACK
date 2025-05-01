package br.edu.upe.huocbackend.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
@Audited
public class DocumentoResultadoLaboratorial {
	
	public DocumentoResultadoLaboratorial() {
		// TODO Auto-generated constructor stub
	}
	
	public DocumentoResultadoLaboratorial(UUID id, String nome, byte[] imagem, LocalDateTime data,
			FormularioMedico formularioMedico) {
		super();
		this.id = id;
		this.nome = nome;
		this.imagem = imagem;
		this.data = data;
		this.formularioMedico = formularioMedico;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	
    @Column(nullable = false)
	private String nome;
	
	@Lob  
    @Column(columnDefinition = "LONGBLOB") 
    private byte[] imagem;
	
	private LocalDateTime data;

	@ManyToOne
	@JoinColumn(name = "idFormularioMedico") 
    private FormularioMedico formularioMedico;
	

}
