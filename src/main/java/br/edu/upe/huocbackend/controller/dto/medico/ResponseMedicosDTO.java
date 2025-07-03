package br.edu.upe.huocbackend.controller.dto.medico;

import br.edu.upe.huocbackend.model.Medico;

import java.util.Set;
import java.util.UUID;

public record ResponseMedicosDTO(UUID huodIdentify,String nome, String email, String CRM, Set<String> especializacao) {

    public ResponseMedicosDTO(Medico medico){
        this(medico.getId(),medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getSetEspecializacaos());
    }
}
