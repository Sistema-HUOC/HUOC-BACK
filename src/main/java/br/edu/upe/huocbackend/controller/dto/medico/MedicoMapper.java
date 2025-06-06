package br.edu.upe.huocbackend.controller.dto.medico;

import br.edu.upe.huocbackend.model.Medico;

public class MedicoMapper {

    public static Medico fromDto(MedicoCriacaoDTO medicoCriacaoDTO){
        Medico medico = new Medico();
        medico.setNome(medicoCriacaoDTO.nome);
        medico.setCRM(medicoCriacaoDTO.crm);
        medico.setEmail(medicoCriacaoDTO.email);
        medico.setCpf(medicoCriacaoDTO.cpf);
        medico.setPassword(medicoCriacaoDTO.password);
        //medico.setEspecializacao();
        return medico;
    }

}
