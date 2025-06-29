package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.instituicao.CreateInstituicaoDto;
import br.edu.upe.huocbackend.controller.dto.instituicao.ResponseInstituicaoDto;
import br.edu.upe.huocbackend.controller.dto.instituicao.UpdateInstituicaoDto;
import br.edu.upe.huocbackend.service.InstituicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instituicoes")
public class InstituicaoController {

    private final InstituicaoService instituicaoService;

    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @PostMapping
    @Operation(summary = "Criação de Instituição", description = "Cria uma nova instituição com o nome fornecido.")
    @ApiResponse(responseCode = "201", description = "Instituição criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios.")
    public ResponseEntity<Void> createInstituicao(@Valid @RequestBody CreateInstituicaoDto dto) {
        instituicaoService.createInstituicao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Listar Instituições", description = "Lista instituições com base nos filtros opcionais: nome da instituição, nome do campus e CNPJ.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de instituições retornada com sucesso")
    })
    public ResponseEntity<List<ResponseInstituicaoDto>> getInstituicao(@RequestParam(required = false) String nomeInstituicao,
                                                                       @RequestParam(required = false) String nomeDoCampos,
                                                                       @RequestParam(required = false) String cnpj) {
        List<ResponseInstituicaoDto> instituicaoDtos = instituicaoService.listInstituicaos(nomeInstituicao,nomeDoCampos,cnpj);
        return ResponseEntity.status(HttpStatus.OK).body(instituicaoDtos);
    }
    @PutMapping
    @Operation(summary = "Atualizar Instituição", description = "Atualiza uma instituição existente com os novos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Instituição atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios."),
            @ApiResponse(responseCode = "404", description = "Instituição não encontrada")
    })
    public ResponseEntity<Void> UpdateInstituicao(@Valid @RequestBody UpdateInstituicaoDto dto) {
        instituicaoService.updateInstituicao(dto);
        return ResponseEntity.noContent().build();
    }
}
