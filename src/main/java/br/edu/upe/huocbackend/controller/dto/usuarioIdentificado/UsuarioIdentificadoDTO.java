package br.edu.upe.huocbackend.controller.dto.usuarioIdentificado;


import java.util.UUID;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioIdentificadoDTO {
	
    private String tipo; // Médico, Paciente, etc
    private String nome;
    private String cpf;
    private UUID userId;
}
