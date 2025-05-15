package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.instituicao.InstituicaoDto;
import br.edu.upe.huocbackend.service.InstituicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Void> createInstituicao(@Valid @RequestBody InstituicaoDto dto) {
        instituicaoService.createInstituicao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
