package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.formSintomatologia.FormularioSintomatologiaDTO;
import br.edu.upe.huocbackend.controller.dto.formSintomatologia.ListarTodosFormularioSintomatologiaDTO;
import br.edu.upe.huocbackend.service.FormularioSintomatologiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/formSintomatologia")
@Tag(name = "FormularioSintomatologia")
public class FormularioSintomatologiaController {

    private final FormularioSintomatologiaService formSintomatologiaService;

    public FormularioSintomatologiaController(FormularioSintomatologiaService formSintomatologiaService) {
        this.formSintomatologiaService = formSintomatologiaService;
    }

    @Operation(
            summary = "Enfermeiro(a) preenche o formulário de sintomatologia",
            description = "Enfermeiro(a) preenche o formulário de sintomatologia com os dados sintomatológicos do paciente"
    )

    @ApiResponse(responseCode = "201", description = "Formulário de sintomatologia criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes")
    @ApiResponse(responseCode = "409", description = "Formulário de sintomatologia deste paciente já existe")
    @PostMapping()
    public ResponseEntity<String> preencherFormSintomatologia(@Valid @RequestBody FormularioSintomatologiaDTO formsintomatologiaDTO, HttpServletResponse response) {
        if(formsintomatologiaDTO.getData() == null) {
            return ResponseEntity.badRequest().body("A data do formulário é obrigatória");
        }

        if(formsintomatologiaDTO.getNumProntuario() == null) {
            return ResponseEntity.badRequest().body("O número do prontuário é obrigatório");
        }

        // Decidir qual informação do paciente o identificaria nesse campo do formulário
        if(formsintomatologiaDTO.getPaciente() == null) {
            return ResponseEntity.badRequest().body("Informação do paciente é obrigatória");
        }

        try {
            formSintomatologiaService.preencherFormSintomatologia(formsintomatologiaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Listar todos os formulários de sintomatologia de um paciente",
            description = "Retorna todos os formulários preenchidos para um paciente específico"
    )
    @ApiResponse(responseCode = "200", description = "Lista de formulários retornada com sucesso")
    @GetMapping("/listar/{idPaciente}")
    public ResponseEntity<List<ListarTodosFormularioSintomatologiaDTO>> listarPorPaciente(@PathVariable UUID idPaciente) {
        List<ListarTodosFormularioSintomatologiaDTO> lista = formSintomatologiaService.listarFormulariosPorPaciente(idPaciente);
        return ResponseEntity.ok(lista);
    }

}
