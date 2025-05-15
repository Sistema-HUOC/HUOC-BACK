package br.edu.upe.huocbackend.controller.dto.pesquisador;

import br.edu.upe.huocbackend.controller.dto.user.UserCreateDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class PesquisadorCreateDto extends UserCreateDto {

    @NotNull(message = "A instituição é obrigatória")
    private UUID idInstituicao;

    @NotEmpty(message = "Pelo menos uma área de atuação deve ser informada")
    private List<UUID> idAreasAtuacao;

    public PesquisadorCreateDto(String nome, String cpf, String email, String password, UUID idInstituicao, List<UUID> idAreasAtuacao) {
        super(nome, cpf, email, password);
        this.idInstituicao = idInstituicao;
        this.idAreasAtuacao = idAreasAtuacao;

    }
}
