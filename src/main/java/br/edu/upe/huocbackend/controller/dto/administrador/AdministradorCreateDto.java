package br.edu.upe.huocbackend.controller.dto.administrador;

import br.edu.upe.huocbackend.controller.dto.user.UserCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "AdministradorCreateDto")
public class AdministradorCreateDto extends UserCreateDto {

    public AdministradorCreateDto(String nome, String cpf, String email, String password) {
        super(nome, cpf, email, password);
    }
}
