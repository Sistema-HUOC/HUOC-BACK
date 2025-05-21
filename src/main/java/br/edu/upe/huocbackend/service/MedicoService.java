package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.formularioMedico.FormularioMedicoCreateDTO;
import br.edu.upe.huocbackend.exception.FormularioMedicoException;
import br.edu.upe.huocbackend.model.FormularioMedico;
import br.edu.upe.huocbackend.model.Medico;
import br.edu.upe.huocbackend.repository.IFormularioMedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final IFormularioMedicoRepository formularioMedicoRepository;

    public MedicoService(IFormularioMedicoRepository formularioMedicoRepository) {this.formularioMedicoRepository = formularioMedicoRepository;}


    @Transactional
    public void criarFormularioMedico(FormularioMedicoCreateDTO formularioMedico) {
        boolean formularioMedic = formularioMedicoRepository.existsById(formularioMedico.id);
        if (formularioMedic) {
            throw new FormularioMedicoException("Formulario com id já criado!");
        }
        formularioMedicoRepository.save(new FormularioMedico(formularioMedico.id, formularioMedico.data, formularioMedico.observacoesAdicionaisFormularioMedico));
    }
}
