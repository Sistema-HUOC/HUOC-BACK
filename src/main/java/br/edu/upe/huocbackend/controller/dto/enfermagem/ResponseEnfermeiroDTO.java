package br.edu.upe.huocbackend.controller.dto.enfermagem;

import br.edu.upe.huocbackend.model.Enfermagem;

public record ResponseEnfermeiroDTO(String nome, String cpf, String email, String coren) {

    public ResponseEnfermeiroDTO(Enfermagem enfermagem) {
        this(enfermagem.getNome(), enfermagem.getCpf(), enfermagem.getEmail(), enfermagem.getCoren());
    }
}
