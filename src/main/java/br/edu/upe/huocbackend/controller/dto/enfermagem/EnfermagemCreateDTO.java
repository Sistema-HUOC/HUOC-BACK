package br.edu.upe.huocbackend.controller.dto.enfermagem;

import br.edu.upe.huocbackend.controller.dto.user.UserCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "EnfermagemCreateDto")
public class EnfermagemCreateDTO extends UserCreateDto {

    public String coren;

    public EnfermagemCreateDTO(String nome, String cpf, String email, String password, String coren) {
        super(nome, cpf, email, password);
        this.coren = coren;
    }
}
