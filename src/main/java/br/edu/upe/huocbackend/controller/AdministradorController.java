package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.controller.dto.pesquisador.PesquisadorCreateDto;
import br.edu.upe.huocbackend.controller.dto.user.UserActivationDTO;
import br.edu.upe.huocbackend.service.AdministradorService;
import br.edu.upe.huocbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adm")
@Tag(name = "Administrador")
public class AdministradorController {

    private final AdministradorService administradorService;
    private final UserService  userService;

    public AdministradorController(AdministradorService administradorService, UserService userService) {
        this.administradorService = administradorService;
        this.userService = userService;
    }

    @Operation(
            summary = "Ativar ou Desativar Usuários",
            description = "Ativar ou Desativar Usuários pelo administrador"
    )
    @ApiResponse(responseCode = "200", description = "Usuários Ativar ou Desativar com sucesso")
    @ApiResponse(responseCode = "400", description = "Conflito no e-mail")
    @PutMapping
    public ResponseEntity<String> updateUserActive(@Valid @RequestBody UserActivationDTO dto){
        userService.updateUserActive(dto);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
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

    @Operation(
            summary = "Administrador cria Enfermeiro(a)",
            description = "Administrador cria um enfermeiro(a) com os detalhes fornecidos"
    )
    @ApiResponse(responseCode = "201", description = "Enfermeiro(a) criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes")
    @ApiResponse(responseCode = "409", description = "Usuário com este e-mail ou CPF já existe")
    @PostMapping("/enfermeiro")
    public  ResponseEntity<String> AdminCreateEnfermeiro(@Valid @RequestBody EnfermagemCreateDTO enfermeiroDto, HttpServletResponse response) {
        if (enfermeiroDto.getEmail() == null || enfermeiroDto.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("O e-mail é obrigatório");
        }

        if (enfermeiroDto.getPassword() == null || enfermeiroDto.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("A senha é obrigatória");
        }

        if (enfermeiroDto.getCoren() == null || enfermeiroDto.getCoren().isEmpty()) {
            return ResponseEntity.badRequest().body("O número do COREN é obrigatório");
        }
        try {
        administradorService.adminCreateEnfermagem(enfermeiroDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Administrador cria Pesquisador(a)",
            description = "Administrador cria um pesquisador(a) com os detalhes fornecidos"
    )
    @ApiResponse(responseCode = "201", description = "Pesquisador  (a) criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes")
    @ApiResponse(responseCode = "409", description = "Usuário com este e-mail ou CPF já existe")
    @PostMapping("pesquisador")
    public  ResponseEntity<String> AdminCreatePesquisador(@Valid @RequestBody PesquisadorCreateDto pesquisadorDto, HttpServletResponse response) {
        try {
            administradorService.adminCreatePesquisador(pesquisadorDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
