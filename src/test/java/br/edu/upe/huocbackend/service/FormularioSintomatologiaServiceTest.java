package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.formularioSintomatologia.FormularioSintomatologiaDTO;
import br.edu.upe.huocbackend.model.AcessLevel;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.repository.IFormularioSintomatologiaRepository;
import br.edu.upe.huocbackend.repository.IPacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FormularioSintomatologiaServiceTest {

    @Mock
    private IFormularioSintomatologiaRepository formSintomatologiaRepository;

    @Mock
    private IPacienteRepository pacienteRepository;

    @InjectMocks
    private FormularioSintomatologiaService formSintomatologiaService;


    @Test
    public void deveSalvarFormularioSintomatologiaComSucesso() {
        UUID idPaciente = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        LocalDateTime data = LocalDateTime.of(2025, 5, 20, 14, 30);
        int numProntuario = 12345;
        String observacoes = "Sem observações";

        FormularioSintomatologiaDTO form = new FormularioSintomatologiaDTO(data, numProntuario, observacoes, idPaciente);

        Paciente paciente = new Paciente();
        paciente.setId(idPaciente);
        paciente.setNome("Claudio");

        when(pacienteRepository.findById(idPaciente)).thenReturn(Optional.of(paciente));

        formSintomatologiaService.save(form);

        verify(formSintomatologiaRepository).save(argThat(formSint ->
                formSint.getData().equals(data) &&
                        formSint.getNumProntuario() == numProntuario &&
                        formSint.getObservacoes().equals(observacoes) &&
                        formSint.getPaciente().getId().equals(idPaciente)
        ));
    }

    @Test
    public void deveRetornarEntityNotFoundQuandoNãoEncontrarIdPacienteInvalido() {
        UUID idPacienteInvalido = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        LocalDateTime data = LocalDateTime.of(2025, 5, 20, 14, 30);
        int numProntuario = 12345;
        String observacoes = "Sem observações";

        FormularioSintomatologiaDTO form = new FormularioSintomatologiaDTO(data, numProntuario, observacoes, idPacienteInvalido);

        when(pacienteRepository.findById(idPacienteInvalido)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            formSintomatologiaService.save(form);
        });

        verify(pacienteRepository).findById(idPacienteInvalido);

        verifyNoInteractions(formSintomatologiaRepository);
    }




}

