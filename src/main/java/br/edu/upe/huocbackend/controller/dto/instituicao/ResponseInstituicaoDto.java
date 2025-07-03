package br.edu.upe.huocbackend.controller.dto.instituicao;

import br.edu.upe.huocbackend.model.Instituicao;

import java.util.UUID;

public record ResponseInstituicaoDto(UUID  huodIdentify,
                                     String nomeInstituicao,
                                     String nomeDoCampos,
                                     String cnpj) {

    public ResponseInstituicaoDto (Instituicao instituicao){
        this(
                instituicao.getId(),
                instituicao.getNomeInstituicao(),
                instituicao.getNomeCampos(),
                instituicao.getCnpj()
        );
    }
}
