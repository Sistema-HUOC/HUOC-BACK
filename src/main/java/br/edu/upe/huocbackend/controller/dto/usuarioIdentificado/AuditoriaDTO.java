package br.edu.upe.huocbackend.controller.dto.usuarioIdentificado;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuditoriaDTO {
    private String entidade;
    private String entidadeId;
    private String acao; // INSERT, UPDATE, DELETE
    private String usuario;
    private LocalDateTime dataHora;
}