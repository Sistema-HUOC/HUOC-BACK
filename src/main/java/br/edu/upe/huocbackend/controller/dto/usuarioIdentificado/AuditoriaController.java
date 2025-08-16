package br.edu.upe.huocbackend.controller.dto.usuarioIdentificado;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {

    @Autowired private UsuarioIdentificadorService identificadorService;
    @Autowired private AuditoriaService auditoriaService;

    @GetMapping("/cpf")
    public ResponseEntity<?> auditarPorCpf(@RequestParam String cpf) {
        var usuario = identificadorService.identificarPorCpf(cpf);
        if (usuario == null) return ResponseEntity.notFound().build();

        var acoes = auditoriaService.buscarAuditoriasPorUserId(usuario.getUserId().toString());

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("usuario", usuario);
        resposta.put("acoes", acoes);
        return ResponseEntity.ok(resposta);
    }
}

