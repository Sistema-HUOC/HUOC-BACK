package br.edu.upe.huocbackend.service;

import org.springframework.stereotype.Service;

import br.edu.upe.huocbackend.controller.dto.paciente.PacienteCreateDTO;
import br.edu.upe.huocbackend.exception.PacienteException;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.model.Prontuario;
import br.edu.upe.huocbackend.repository.IPacienteRepository;
import br.edu.upe.huocbackend.repository.ProntuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class EnfermagemService {

    private final IPacienteRepository pacienteRepository;
    private final ProntuarioRepository prontuarioRepository;

    public EnfermagemService(IPacienteRepository pacienteRepository, ProntuarioRepository prontuarioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.prontuarioRepository = prontuarioRepository;
    }

    @Transactional
    public void criarPaciente(PacienteCreateDTO paciente) {
        boolean pacient = pacienteRepository.existsByCpf(paciente.cpf);
        if (pacient) {
            throw new PacienteException("Paciente já cadastrado(a)");
        }
        
       Paciente pacienteSalvo = pacienteRepository.save(new Paciente(paciente.nome, paciente.cpf, paciente.dataNasc, paciente.sexo,
                paciente.telefone, paciente.htvl1, paciente.htvl2, paciente.numProntuario, paciente.endereco));
        
        
        prontuarioRepository.save(new Prontuario(pacienteSalvo));
    }
}
