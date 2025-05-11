package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.paciente.PacienteCreateDTO;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.repository.IEnfermagemRepository;
import br.edu.upe.huocbackend.repository.IPacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EnfermagemServiceTest {

    private EnfermagemService enfermagemService;
    private IPacienteRepository pacienteRepository;
    private IEnfermagemRepository enfermagemRepository;

    @BeforeEach
    void setUp() {
        pacienteRepository = mock(IPacienteRepository.class);
        enfermagemRepository = mock(IEnfermagemRepository.class);
        enfermagemService = new EnfermagemService(enfermagemRepository, pacienteRepository);
    }

    @Test
    void testEnfermagemCreatePaciente() throws Exception {
        // Arrange
        PacienteCreateDTO dto = new PacienteCreateDTO();
        dto.setNome("João da Silva");
        dto.setCpf("12345678900");

        // Converte String para Date usando SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = sdf.parse("1990-01-01");
        dto.setDataNasc(dataNascimento);

        dto.setSexo("M");
        dto.setNumero("81999999999");
        dto.setHtvl1("Negativo");
        dto.setHtvl2("Negativo");
        dto.setNumProntuario(123);

        // Campos opcionais (podem ser null ou mocks simples se preferir)
        dto.setEnfermagem(null);
        dto.setEndereco(null);
        dto.setExames(null);
        dto.setFormularioSintomatologia(null);

        Paciente pacienteSalvo = new Paciente();
        pacienteSalvo.setNome(dto.getNome());
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteSalvo);

        // Act
        Paciente resultado = enfermagemService.EnfermagemCreatePaciente(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals("João da Silva", resultado.getNome());

        // Verifica se o paciente foi passado corretamente ao save
        ArgumentCaptor<Paciente> captor = ArgumentCaptor.forClass(Paciente.class);
        verify(pacienteRepository, times(1)).save(captor.capture());
        Paciente pacienteCapturado = captor.getValue();

        assertEquals("João da Silva", pacienteCapturado.getNome());
        assertEquals("12345678900", pacienteCapturado.getCpf());
        assertEquals(dataNascimento, pacienteCapturado.getDataNasc());
        assertEquals("M", pacienteCapturado.getSexo());
        assertEquals("81999999999", pacienteCapturado.getNumero());
        assertEquals("Negativo", pacienteCapturado.getHtvl1());
        assertEquals("Negativo", pacienteCapturado.getHtvl2());
        assertEquals(123, pacienteCapturado.getNumProntuario());
    }
}
