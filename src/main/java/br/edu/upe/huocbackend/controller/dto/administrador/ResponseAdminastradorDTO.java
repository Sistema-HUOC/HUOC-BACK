package br.edu.upe.huocbackend.controller.dto.administrador;

import br.edu.upe.huocbackend.model.Administrador;

public record ResponseAdminastradorDTO(String nome, String cpf, String email) {

    public ResponseAdminastradorDTO(Administrador administrador) {
        this(administrador.getNome(), administrador.getCpf(), administrador.getEmail());
    }
}
