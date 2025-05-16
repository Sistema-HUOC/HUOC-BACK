package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.paciente.PacienteCreateDTO;
import br.edu.upe.huocbackend.exception.PacienteException;
import br.edu.upe.huocbackend.model.Endereco;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.repository.IPacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnfermagemServiceTest {

    private IPacienteRepository pacienteRepository;
    private EnfermagemService enfermagemService;

    @BeforeEach
    public void setUp() {
        pacienteRepository = Mockito.mock(IPacienteRepository.class);
        enfermagemService = new EnfermagemService(pacienteRepository);
    }

    @Test
    public void deveCriarPacienteComSucesso() {
        // Arrange
        PacienteCreateDTO dto = criarPacienteDTO();
        when(pacienteRepository.existsByCpf(dto.getCpf())).thenReturn(false);

        // Act
        enfermagemService.criarPaciente(dto);

        // Assert
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    public void deveLancarExcecaoQuandoPacienteJaExiste() {
        // Arrange
        PacienteCreateDTO dto = criarPacienteDTO();
        when(pacienteRepository.existsByCpf(dto.getCpf())).thenReturn(true);

        // Act & Assert
        PacienteException exception = assertThrows(PacienteException.class, () -> {
            enfermagemService.criarPaciente(dto);
        });

        assertEquals("Paciente já cadastrado(a)", exception.getMessage());
        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    private PacienteCreateDTO criarPacienteDTO() {
        return new PacienteCreateDTO(
                "Maria da Silva",
                "123.456.789-00",
                LocalDate.of(1990, 1, 1),
                "Feminino",
                "81999999999",
                true,
                false,
                new Endereco("Rua A", "123", "Apto 1", "Bairro X", "Cidade Y", "PE", "50000-000"),
                1001
        );
    }
}
