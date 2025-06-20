package br.edu.upe.huocbackend.controller.dto.enfermagem;

import br.edu.upe.huocbackend.controller.dto.user.UserCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "EnfermagemCreateDto")
public class EnfermagemCreateDTO extends UserCreateDto {

    @NotBlank(message = "O número do COREN é obrigatório.")
    @Pattern(regexp = "\\d{4,6}-[A-Z]{2}", message = "O COREN deve seguir o formato '123456-PE'.")
    public String coren;

    public EnfermagemCreateDTO(String nome, String cpf, String email, String password, String coren) {
        super(nome, cpf, email, password);
        this.coren = coren;
    }
}
