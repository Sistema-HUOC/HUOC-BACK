package br.edu.upe.huocbackend.model;

import java.util.UUID;

import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Audited
public class Prontuario {
	
	public Prontuario() {
		// TODO Auto-generated constructor stub
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id; 
	
	private String infoProntuario;
	 
	 
	@ManyToOne
	@JoinColumn(name = "idFormularioMedico") 
    private FormularioMedico formularioMedico;


    private String id_Paciente;

    private String id_Pesquisador;
}
