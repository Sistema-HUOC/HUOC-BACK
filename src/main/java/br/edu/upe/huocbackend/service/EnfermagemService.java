package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.paciente.PacienteCreateDTO;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.repository.IEnfermagemRepository;
import br.edu.upe.huocbackend.repository.IPacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EnfermagemService {

    private final IEnfermagemRepository enfermagemRepository ;
    private final IPacienteRepository pacienteRepository ;

    public EnfermagemService(IEnfermagemRepository enfermagemRepository, IPacienteRepository pacienteRepository) {
        this.enfermagemRepository = enfermagemRepository ;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public Paciente EnfermagemCreatePaciente(PacienteCreateDTO pacienteDTO){
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setDataNasc(pacienteDTO.getDataNasc());
        paciente.setSexo(pacienteDTO.getSexo());
        paciente.setNumero(pacienteDTO.getNumero());
        paciente.setHtvl1(pacienteDTO.getHtvl1());
        paciente.setHtvl2(pacienteDTO.getHtvl2());
        paciente.setNumProntuario(pacienteDTO.getNumProntuario());
        paciente.setEnfermagem(pacienteDTO.getEnfermagem());
        paciente.setEndereco(pacienteDTO.getEndereco());
        paciente.setExames(pacienteDTO.getExames());
        paciente.setFormularioSintomatologia(pacienteDTO.getFormularioSintomatologia());

        return pacienteRepository.save(paciente);

    }
}
