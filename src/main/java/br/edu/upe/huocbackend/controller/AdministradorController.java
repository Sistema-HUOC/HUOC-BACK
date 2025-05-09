package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adm")
@Tag(name = "Administrador")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @Operation(
            summary = "Registro de administrador",
            description = "Cria um novo administrador com os detalhes fornecidos"
    )
    @ApiResponse(responseCode = "201", description = "Administrador criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes")
    @ApiResponse(responseCode = "409", description = "Usuário com este e-mail ou CPF já existe")
    @PostMapping()
    public ResponseEntity<String> CreateAdmin(@Valid @RequestBody AdministradorCreateDto dto, HttpServletResponse response) {
        if(dto.getEmail() == null || dto.getEmail().isEmpty() ||
                dto.getPassword() == null || dto.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("E-mail e senha são obrigatórios");
        }
        try {
            administradorService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }
}
