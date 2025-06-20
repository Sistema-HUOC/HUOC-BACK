package br.edu.upe.huocbackend.controller.dto.administrador;

import br.edu.upe.huocbackend.model.Administrador;

public record ResponseAdminastradorDto(String nome, String cpf, String email) {

    public ResponseAdminastradorDto(Administrador administrador) {
        this(administrador.getNome(), administrador.getCpf(), administrador.getEmail());
    }
}
