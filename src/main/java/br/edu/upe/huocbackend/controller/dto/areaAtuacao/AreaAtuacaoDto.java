package br.edu.upe.huocbackend.controller.dto.areaAtuacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AreaAtuacaoDto {

    @NotBlank(message = "O nome da área não pode ser vazio")
    @Schema(description = "Nome da área de atuação", example = "Neurociência")
    private String nomeArea;


    public AreaAtuacaoDto(String nomeArea) {
        this.nomeArea = nomeArea;
    }
}
