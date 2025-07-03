package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.controller.dto.administrador.ResponseAdminastradorDTO;
import br.edu.upe.huocbackend.controller.dto.administrador.UpdateAdministradorDto;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.controller.dto.enfermagem.ResponseEnfermeiroDTO;
import br.edu.upe.huocbackend.controller.dto.enfermagem.UpdateEnfermeiroDTO;
import br.edu.upe.huocbackend.controller.dto.medico.CreateEspecializacaoDTO;
import br.edu.upe.huocbackend.controller.dto.medico.MedicoCriacaoDTO;
import br.edu.upe.huocbackend.controller.dto.medico.ResponseMedicosDTO;
import br.edu.upe.huocbackend.controller.dto.medico.UpdateMedicoDto;
import br.edu.upe.huocbackend.controller.dto.pesquisador.PesquisadorCreateDto;
import br.edu.upe.huocbackend.controller.dto.pesquisador.UpdatePesquisadorDto;
import br.edu.upe.huocbackend.controller.dto.user.UserActivationDTO;
import br.edu.upe.huocbackend.service.AdministradorService;
import br.edu.upe.huocbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
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


    @Operation(
            summary = "Listar administradores",
            description = "Retorna uma lista paginada de administradores filtrando opcionalmente por nome, e-mail e status de atividade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de administradores retornada com sucesso")
    })
    @GetMapping("/administradores")
    public ResponseEntity<Page<ResponseAdminastradorDTO>> listAdms(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "true") boolean ativo,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(administradorService.listAllAdministrador(nome, email, ativo, pageable));
    }


    @Operation(
            summary = "Listar enfermeiros(as)",
            description = "Retorna uma lista paginada de enfermeiros(as), com possibilidade de filtragem por nome, e-mail, COREN e status de atividade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de enfermeiros(as) retornada com sucesso")
    })
    @GetMapping("/enfermeiros")
    public ResponseEntity<Page<ResponseEnfermeiroDTO>> listEnfermeiros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String coren,
            @RequestParam(required = false, defaultValue = "true") boolean ativo,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(administradorService.listAllEnfermeiros(nome, email, coren, ativo, pageable));
    }


    @Operation(
            summary = "Listar médicos(as)",
            description = "Retorna uma lista paginada de médicos(as), com possibilidade de filtragem por nome, e-mail, CRM, especialização e status de atividade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de médicos(as) retornada com sucesso")
    })
    @GetMapping("/medicos")
    public ResponseEntity<Page<ResponseMedicosDTO>> listMedicos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String crm,
            @RequestParam(required = false) String especializacao,
            @RequestParam(required = false, defaultValue = "true") boolean ativo,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(administradorService.listAllMedicos(nome, email, crm, especializacao, ativo, pageable));
    }


    @Operation(
            summary = "Listar especializações",
            description = "Retorna a lista de nomes de todas as especializações cadastradas no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de especializações retornada com sucesso")
    })
    @GetMapping("/especializacoes")
    public ResponseEntity<List<String>> listEspecializacoes() {
        return ResponseEntity.ok(administradorService.listEspecializacoes());
    }



    @Operation(
            summary = "Atualiza informações de um(a) médico(a)",
            description = "Permite que um administrador atualize os dados de um(a) médico(a), incluindo nome, CRM, email, senha, CPF e especializações."
    )
    @ApiResponse(responseCode = "204", description = "Médico atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes ou inválidos")
    @ApiResponse(responseCode = "404", description = "Médico não encontrado para o e-mail informado")
    @ApiResponse(responseCode = "409", description = "Conflito ao atualizar especializações ou outros dados")
    @PutMapping("/medico")
    public ResponseEntity<Void> updateMedico(@Valid @RequestBody UpdateMedicoDto dto) {
        administradorService.updateMedico(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Atualiza os dados de um(a) pesquisador(a)",
            description = "Permite que um administrador atualize os dados de um(a) pesquisador(a), como nome, e-mail, CPF, instituição e áreas de atuação."
    )
    @ApiResponse(responseCode = "204", description = "Pesquisador(a) atualizado(a) com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes ou mal formatados)")
    @ApiResponse(responseCode = "404", description = "Pesquisador(a), instituição ou área de atuação não encontrada")
    @ApiResponse(responseCode = "409", description = "Conflito ao tentar atualizar (ex: e-mail já em uso)")
    @PutMapping("/pesquisador")
    public ResponseEntity<Void> updatePesquisador(@Valid @RequestBody UpdatePesquisadorDto dto){
        administradorService.updatePesquisador(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Atualizar administrador",
            description = "Atualiza os dados de um administrador existente com base no identificador e nas informações fornecidas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Administrador atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (campos obrigatórios ausentes ou mal formatados)"),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflito ao atualizar (ex: e-mail já em uso)")
    })
    @PutMapping("/administrador")
    public ResponseEntity<Void> updateAdministrador(@Valid @RequestBody UpdateAdministradorDto dto) {
        administradorService.updateAdministrador(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/enfermagem")
    public ResponseEntity<Void> updateEnfermagem(@Valid @RequestBody UpdateEnfermeiroDTO dto) {
        administradorService.updateEnfermagem(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
