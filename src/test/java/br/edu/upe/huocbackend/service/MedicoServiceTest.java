package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.formularioMedico.FormularioMedicoCreateDTO;
import br.edu.upe.huocbackend.exception.FormularioMedicoException;
import br.edu.upe.huocbackend.model.FormularioMedico;
import br.edu.upe.huocbackend.repository.IFormularioMedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicoServiceTest {

    private IFormularioMedicoRepository formularioMedicoRepository;
    private MedicoService medicoService;

    @BeforeEach
    public void setUp() {
        formularioMedicoRepository = mock(IFormularioMedicoRepository.class);
        medicoService = new MedicoService(formularioMedicoRepository);
    }

    @Test
    public void deveCriarFormularioMedicoComSucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        FormularioMedicoCreateDTO dto = criarFormularioMedicoDTO(id);
        when(formularioMedicoRepository.existsById(id)).thenReturn(false);

        // Act
        medicoService.criarFormularioMedico(dto);

        // Assert
        verify(formularioMedicoRepository, times(1)).save(any(FormularioMedico.class));
    }

    @Test
    public void deveLancarExcecaoQuandoFormularioJaExiste() {
        // Arrange
        UUID id = UUID.randomUUID();
        FormularioMedicoCreateDTO dto = criarFormularioMedicoDTO(id);
        when(formularioMedicoRepository.existsById(id)).thenReturn(true);

        // Act & Assert
        FormularioMedicoException exception = assertThrows(FormularioMedicoException.class, () -> {
            medicoService.criarFormularioMedico(dto);
        });

        assertEquals("Formulario com id já criado!", exception.getMessage());
        verify(formularioMedicoRepository, never()).save(any(FormularioMedico.class));
    }

    private FormularioMedicoCreateDTO criarFormularioMedicoDTO(UUID id) {
        FormularioMedicoCreateDTO dto = new FormularioMedicoCreateDTO(LocalDateTime.now(), "Paciente apresenta febre alta.");
        dto.setId(id);
        return dto;
    }
}
