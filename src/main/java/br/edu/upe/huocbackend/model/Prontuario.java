package br.edu.upe.huocbackend.model;

import java.util.UUID;

import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Audited
@Getter
@Setter
public class Prontuario {
	
	public Prontuario() {
		// TODO Auto-generated constructor stub
	}
	




	public Prontuario(FormularioMedico formularioMedico) {
		this.formularioMedico = formularioMedico;
	}

	public Prontuario(Paciente paciente) {
		this.paciente = paciente;
	}

	public Prontuario(Pesquisador pesquisador)
	{this.pesquisador= pesquisador;}



	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id; 
	
	private String infoProntuario;
	 
	 
	@ManyToOne
	@JoinColumn(name = "idFormularioMedico") 
    private FormularioMedico formularioMedico;

	@ManyToOne
	@JoinColumn(name = "idPaciente")
    private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "idPesquisador")
    private Pesquisador pesquisador;
}
