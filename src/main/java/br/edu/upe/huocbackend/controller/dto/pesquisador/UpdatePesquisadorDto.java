package br.edu.upe.huocbackend.controller.dto.pesquisador;

import java.util.List;
import java.util.UUID;

public record UpdatePesquisadorDto(
                                   UUID huodIdentify,
                                   String nome,
                                   String cpf,
                                   String email,
                                   UUID idInstituicao,
                                   List<UUID> idAreasAtuacao
                                   ) {
}
