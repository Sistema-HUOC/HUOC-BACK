package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.paciente.PacienteCreateDTO;
import br.edu.upe.huocbackend.exception.PacienteException;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.repository.IPacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EnfermagemService {

    private final IPacienteRepository pacienteRepository ;

    public EnfermagemService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public void criarPaciente(PacienteCreateDTO paciente) {
        boolean pacient = pacienteRepository.existsByCpf(paciente.cpf);
        if(pacient) {
            throw new PacienteException("Paciente já cadastrado(a)");
        }
        pacienteRepository.save(new Paciente(paciente.nome, paciente.cpf, paciente.dataNasc, paciente.sexo,
                paciente.telefone, paciente.htvl1, paciente.htvl2, paciente.numProntuario, paciente.endereco));
    }
}
