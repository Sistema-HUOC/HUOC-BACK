package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.paciente.PacienteCreateDTO;
import br.edu.upe.huocbackend.service.EnfermagemService;
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
@RequestMapping("/api/enfermagem")
@Tag(name = "Enfermagem")
public class EnfermagemController {

    private final EnfermagemService enfermagemService;

    public EnfermagemController(EnfermagemService enfermagemService) {
        this.enfermagemService = enfermagemService;
    }

    @Operation(
            summary = "Enfermeiro(a) cria Paciente",
            description = "Enfermeiro(a) cria um paciente com os detalhes fornecidos"
    )
    @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes")
    @ApiResponse(responseCode = "409", description = "Paciente com este CPF já existe")
    @PostMapping("paciente")
    public ResponseEntity<String> criarPaciente(@Valid @RequestBody PacienteCreateDTO pacienteDTO, HttpServletResponse response) {
        if (pacienteDTO.getNome() == null || pacienteDTO.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("O nome é obrigatório");
        }

        if (pacienteDTO.getCpf() == null || pacienteDTO.getCpf().isEmpty()) {
            return ResponseEntity.badRequest().body("O CPF é obrigatório");
        }

        if (pacienteDTO.getDataNasc() == null) {
            return ResponseEntity.badRequest().body("A data de nascimento é obrigatória");
        }

        if (pacienteDTO.getSexo() == null || pacienteDTO.getSexo().isEmpty()) {
            return ResponseEntity.badRequest().body("O sexo é obrigatório");
        }

        if (pacienteDTO.getTelefone() == null || pacienteDTO.getTelefone().isEmpty()) {
            return ResponseEntity.badRequest().body("O número de telefone é obrigatório");
        }

        if (pacienteDTO.getHtvl1() == null || pacienteDTO.getHtvl2() == null) {
            return ResponseEntity.badRequest().body("Os campos de HTVL 1 e HTVL 2 são obrigatórios");
        }

        if (pacienteDTO.getNumProntuario() == null) {
            return ResponseEntity.badRequest().body("O número de prontuário é obrigatório");
        }

        if (pacienteDTO.getEndereco() == null) {
            return ResponseEntity.badRequest().body("O endereço é obrigatório");
        }

        try {
            enfermagemService.criarPaciente(pacienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
