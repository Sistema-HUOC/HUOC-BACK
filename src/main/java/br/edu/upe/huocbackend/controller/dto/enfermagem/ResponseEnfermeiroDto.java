package br.edu.upe.huocbackend.controller.dto.enfermagem;

import br.edu.upe.huocbackend.model.Enfermagem;

public record ResponseEnfermeiroDto(String nome, String cpf, String email,String coren) {

    public ResponseEnfermeiroDto(Enfermagem enfermagem) {
        this(enfermagem.getNome(), enfermagem.getCpf(), enfermagem.getEmail(), enfermagem.getCoren());
    }
}
