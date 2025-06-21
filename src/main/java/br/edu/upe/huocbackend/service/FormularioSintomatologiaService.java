package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.formSintomatologia.FormularioSintomatologiaDTO;
import br.edu.upe.huocbackend.controller.dto.formSintomatologia.ListarTodosFormularioSintomatologiaDTO;
import br.edu.upe.huocbackend.exception.formSintomatologiaException;
import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import br.edu.upe.huocbackend.repository.IFormularioSintomatologiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FormularioSintomatologiaService {

    private final IFormularioSintomatologiaRepository formSintomatologiaRepository;

    public FormularioSintomatologiaService(IFormularioSintomatologiaRepository formSintomatologiaRepository) {
        this.formSintomatologiaRepository = formSintomatologiaRepository;
    }

    @Transactional
    public void preencherFormSintomatologia(FormularioSintomatologiaDTO formSintomatologia) {
        boolean formSintoma = formSintomatologiaRepository.existsByPaciente(formSintomatologia.paciente);
        if (formSintoma) {
            throw new formSintomatologiaException("Formulário de sintomatologia deste paciente já foi cadastrado");
        }
        formSintomatologiaRepository.save(new FormularioSintomatologia(formSintomatologia.catGeral, formSintomatologia.catRespiratorio,
                formSintomatologia.catInflamatorio, formSintomatologia.catCardiovascular, formSintomatologia.catGastrointestinal,
                formSintomatologia.catNeurologico, formSintomatologia.catMusculoesqueletico, formSintomatologia.catHematologico,
                formSintomatologia.catPsiquiatrico, formSintomatologia.data, formSintomatologia.numProntuario, formSintomatologia.observacoes,
                formSintomatologia.paciente));
    }

    public List<ListarTodosFormularioSintomatologiaDTO> listarFormulariosPorPaciente(UUID pacienteId) {
        List<FormularioSintomatologia> formularios = formSintomatologiaRepository.findAllByPacienteId(pacienteId);

        return formularios.stream()
                .map(ListarTodosFormularioSintomatologiaDTO::new)
                .collect(Collectors.toList());
    }
}
