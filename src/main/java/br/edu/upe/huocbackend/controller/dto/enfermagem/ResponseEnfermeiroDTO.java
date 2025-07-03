package br.edu.upe.huocbackend.controller.dto.enfermagem;

import br.edu.upe.huocbackend.model.Enfermagem;

import java.util.UUID;

public record ResponseEnfermeiroDTO(UUID huodIdentify,String nome, String cpf, String email, String coren) {

    public ResponseEnfermeiroDTO(Enfermagem enfermagem) {
        this(enfermagem.getId(),enfermagem.getNome(), enfermagem.getCpf(), enfermagem.getEmail(), enfermagem.getCoren());
    }
}
