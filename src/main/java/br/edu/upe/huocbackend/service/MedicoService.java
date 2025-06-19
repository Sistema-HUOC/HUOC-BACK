package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.formularioMedico.FormularioMedicoCreateDTO;
import br.edu.upe.huocbackend.controller.dto.paciente.PacienteMapper;
import br.edu.upe.huocbackend.controller.dto.paciente.PacienteResponse;
import br.edu.upe.huocbackend.exception.FormularioMedicoException;
import br.edu.upe.huocbackend.model.FormularioMedico;
import br.edu.upe.huocbackend.repository.IFormularioMedicoRepository;
import br.edu.upe.huocbackend.repository.IPacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoService {

    private final IFormularioMedicoRepository formularioMedicoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;

    public MedicoService(IFormularioMedicoRepository formularioMedicoRepository) {
        this.formularioMedicoRepository = formularioMedicoRepository;
    }


    @Transactional
    public void criarFormularioMedico(FormularioMedicoCreateDTO formularioMedico) {
        boolean formularioMedic = formularioMedicoRepository.existsById(formularioMedico.id);
        if (formularioMedic) {
            throw new FormularioMedicoException("Formulario com id já criado!");
        }
        formularioMedicoRepository.save(new FormularioMedico(formularioMedico.id, formularioMedico.data, formularioMedico.observacoesAdicionaisFormularioMedico));
    }

    @org.springframework.transaction.annotation.Transactional
    public Page<PacienteResponse> listarPacientes(int page){
        return pacienteRepository.findAll(PageRequest.of(page,15)).map(PacienteMapper::toResponse);
    }
    @org.springframework.transaction.annotation.Transactional
    public Optional<PacienteResponse> getPacientePorNome(String nome){
        return pacienteRepository.findByNome(nome).map(PacienteMapper::toResponse);
    }
    @org.springframework.transaction.annotation.Transactional
    public Optional<PacienteResponse> getPacientePorCpf(String cpf){
        return pacienteRepository.findByNome(cpf).map(PacienteMapper::toResponse);
    }
}
