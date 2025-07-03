package br.edu.upe.huocbackend.controller.dto.administrador;

import br.edu.upe.huocbackend.model.Administrador;

import java.util.UUID;

public record ResponseAdminastradorDTO(UUID huodIdentify, String nome, String cpf, String email) {

    public ResponseAdminastradorDTO(Administrador administrador) {
        this(administrador.getId() ,administrador.getNome(), administrador.getCpf(), administrador.getEmail());
    }
}
