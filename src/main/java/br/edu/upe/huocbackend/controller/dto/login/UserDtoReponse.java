package br.edu.upe.huocbackend.controller.dto.login;

import br.edu.upe.huocbackend.model.AcessLevel;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "User login response")
public class UserDtoReponse {

    @Schema(description = "JWT authentication token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    public UUID userId;

    @Schema(description = "User's display name", example = "John Doe")
    public String name;

    @Schema(description = "User's email address", example = "john@email.com")
    public String username;

    @Schema(description = "User's access level")
    public AcessLevel accessLevel;
}
