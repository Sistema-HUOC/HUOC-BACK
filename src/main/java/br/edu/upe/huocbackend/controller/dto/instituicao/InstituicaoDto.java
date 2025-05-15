package br.edu.upe.huocbackend.controller.dto.instituicao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class InstituicaoDto {


    @NotBlank(message = "O nome da instituição é obrigatório")
    @Schema(description = "Nome da instituição", example = "Universidade Federal de Pernambuco")
    private String nomeInstituicao;

    public InstituicaoDto(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }
}
