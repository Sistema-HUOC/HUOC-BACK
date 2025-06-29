package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.paciente.PacienteResponse;
import br.edu.upe.huocbackend.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/api/adm")
@Tag(name = "Médico")
public class MedicoController {
    private MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping("/paciente")
    @Operation(
            summary = "Lista pacientes (paginado)",
            description = "Lista todos os pacientes de forma paginada"
    )
    @ApiResponse(responseCode = "200", description = "Consumido com sucesso")
    public ResponseEntity<Page<PacienteResponse>> getPaciente(@RequestParam(value = "page",required = false,defaultValue = "0") int page) {
        return ResponseEntity.ok(medicoService.listarPacientes(page));
    }
    @GetMapping("/paciente/nome/{nome}")
    @Operation(
            summary = "Pega paciente pelo nome",
            description = "Pega o paciente referente ao nome fornecido"
    )
    @ApiResponse(responseCode = "200", description = "Consumido com sucesso")
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    public ResponseEntity<PacienteResponse> getPacientePorNome(@PathVariable("nome") String nome) {
        var p = medicoService.getPacientePorNome(nome);
        if(p.isPresent())
            return ResponseEntity.ok(p.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/paciente/cpf/{cpf}")
    @Operation(
            summary = "Pega paciente pelo cpf",
            description = "Pega o paciente referente ao cpf fornecido"
    )
    @ApiResponse(responseCode = "200", description = "Consumido com sucesso")
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    public ResponseEntity<PacienteResponse> getPacientePorCpf(@PathVariable("cpf") String cpf) {
        var p = medicoService.getPacientePorCpf(cpf);
        if(p.isPresent())
            return ResponseEntity.ok(p.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
