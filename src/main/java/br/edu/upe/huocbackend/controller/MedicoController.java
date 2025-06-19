package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.paciente.PacienteResponse;
import br.edu.upe.huocbackend.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
