package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.controller.dto.administrador.ResponseAdminastradorDTO;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.controller.dto.enfermagem.ResponseEnfermeiroDTO;
import br.edu.upe.huocbackend.controller.dto.medico.CreateEspecializacaoDTO;
import br.edu.upe.huocbackend.controller.dto.medico.MedicoCriacaoDTO;
import br.edu.upe.huocbackend.controller.dto.medico.ResponseMedicosDTO;
import br.edu.upe.huocbackend.controller.dto.pesquisador.PesquisadorCreateDto;
import br.edu.upe.huocbackend.controller.dto.user.UserActivationDTO;
import br.edu.upe.huocbackend.service.AdministradorService;
import br.edu.upe.huocbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            administradorService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(
            summary = "Registro de medico",
            description = "Cria um novo medico com os detalhes fornecidos"
    )
    @ApiResponse(responseCode = "201", description = "Medico criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes")
    @ApiResponse(responseCode = "409", description = "Usuário com este e-mail ou CPF já existe")
    @PostMapping("/medico")
    public ResponseEntity<String> CreateMedico(@Valid @RequestBody MedicoCriacaoDTO dto, HttpServletResponse response) {
        administradorService.adminCreateMedico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Registro de Especialização",
            description = "Cria um novo Especialização com os detalhes fornecidos"
    )
    @ApiResponse(responseCode = "201", description = "Especialização criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes")
    @ApiResponse(responseCode = "409", description = "Especialização já existe")
    @PostMapping("/especializacao")
    public ResponseEntity<String> CreateEspecializacao(@Valid @RequestBody CreateEspecializacaoDTO dto, HttpServletResponse response) {
        administradorService.adminCreateEspecializacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
        administradorService.adminCreateEnfermagem(enfermeiroDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
        administradorService.adminCreatePesquisador(pesquisadorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/administradores")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Adminastradores")
    public ResponseEntity<Page<ResponseAdminastradorDTO>> listAdms(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false , defaultValue = "true") boolean ativo,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(administradorService.listAllAdministrador(nome, email, ativo,pageable));
    }

    @GetMapping("/enfermeiros")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Enfermeiros")
    public ResponseEntity<Page<ResponseEnfermeiroDTO>> listEnfermeiros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String coren,
            @RequestParam(required = false , defaultValue = "true") boolean ativo,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(administradorService.listAllEnfermeiros(nome,email,coren,ativo,pageable));
    }

    @GetMapping("/Medicos")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Medicos")
    public ResponseEntity<Page<ResponseMedicosDTO>> listMedicos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String crm,
            @RequestParam(required = false) String especializacao,
            @RequestParam(required = false , defaultValue = "true") boolean ativo,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(administradorService.listAllMedicos(nome,email,crm,especializacao,ativo,pageable));
    }

    @GetMapping("/especializacoes")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Especializacoes")
    public ResponseEntity<List<String>> listEspecializacoes(){
        return ResponseEntity.ok(administradorService.listEspecializacoes());
    }
}
