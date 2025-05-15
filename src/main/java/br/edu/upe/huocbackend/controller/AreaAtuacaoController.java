package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.areaAtuacao.AreaAtuacaoDto;
import br.edu.upe.huocbackend.service.AreaAtuacaoService;
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
@RequestMapping("api/areas-atuacao")
public class AreaAtuacaoController {


    private final AreaAtuacaoService areaAtuacaoService;

    public AreaAtuacaoController(AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

    @Operation(
            summary = "Criação de Área de Atuação",
            description = "Cria uma nova área de atuação com o nome fornecido."
    )
    @ApiResponse(responseCode = "201", description = "Área de atuação criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios.")

    @PostMapping
    public ResponseEntity<Void> CreateAreaAtuacao(@Valid @RequestBody AreaAtuacaoDto dto) {
        areaAtuacaoService.CreateAreaAtuacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
