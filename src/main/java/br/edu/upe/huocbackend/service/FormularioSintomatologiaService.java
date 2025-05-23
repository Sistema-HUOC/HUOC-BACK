package br.edu.upe.huocbackend.service;


import br.edu.upe.huocbackend.controller.dto.formularioSintomatologia.FormularioSintomatologiaDTO;
import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.repository.IFormularioSintomatologiaRepository;
import br.edu.upe.huocbackend.repository.IPacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormularioSintomatologiaService {

    private final IFormularioSintomatologiaRepository formSintomatologiaRepository;
    private final IPacienteRepository pacienteRepository;

    public FormularioSintomatologiaService(IFormularioSintomatologiaRepository formSintomatologiaRepository, IPacienteRepository pacienteRepository) {
        this.formSintomatologiaRepository = formSintomatologiaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public void save(FormularioSintomatologiaDTO formSintomatologia) {
        Paciente paciente = pacienteRepository.findById(formSintomatologia.getIdPaciente()).orElseThrow(()
                -> new EntityNotFoundException("Paciente não encontrado"));

        formSintomatologiaRepository.save(new FormularioSintomatologia(formSintomatologia.getData(), formSintomatologia.getNumProntuario()
        ,formSintomatologia.getObservacoes(), paciente));


    }
}
