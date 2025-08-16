package br.edu.upe.huocbackend.controller.dto.usuarioIdentificado;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriaService {

    @Autowired private AuditoriaCustomRepository auditoriaRepo;

    public List<AuditoriaDTO> buscarAuditoriasPorUserId(String userId) {
        List<AuditoriaDTO> resultado = new ArrayList<>();
        resultado.addAll(mapear(auditoriaRepo.buscarAuditoriaPaciente(userId)));

        // Adicione outros: medico, formulario, exame, etc
        return resultado.stream()
                .sorted(Comparator.comparing(AuditoriaDTO::getDataHora).reversed())
                .collect(Collectors.toList());
    }

    private List<AuditoriaDTO> mapear(List<Object[]> dados) {
        return dados.stream().map(obj -> {
            AuditoriaDTO dto = new AuditoriaDTO();
            dto.setEntidade((String) obj[0]);
            dto.setEntidadeId((String) obj[1]);  // agora String UUID em hex
            dto.setAcao((String) obj[2]);
            dto.setUsuario((String) obj[3]);
            dto.setDataHora(((Timestamp) obj[4]).toLocalDateTime());
            return dto;
        }).collect(Collectors.toList());
    }

}

