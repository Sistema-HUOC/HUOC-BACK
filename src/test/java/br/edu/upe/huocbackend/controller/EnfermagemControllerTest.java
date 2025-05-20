package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.config.security.SecurityConfig;
import br.edu.upe.huocbackend.config.security.TokenService;
import br.edu.upe.huocbackend.controller.dto.paciente.PacienteCreateDTO;
import br.edu.upe.huocbackend.exception.PacienteException;
import br.edu.upe.huocbackend.model.Endereco;
import br.edu.upe.huocbackend.service.EnfermagemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import({TokenService.class})
@ImportAutoConfiguration(classes = {SecurityConfig.class})
public class EnfermagemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EnfermagemService enfermagemService;

    @MockitoBean
    private TokenService tokenServive;

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    public void deveCriarPacienteComSucesso() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Carlos da Silva", "29357232443", dataNasc, "Masculino",
                "81 999999999", false, true, endereco, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isCreated());

        verify(enfermagemService, times(1)).criarPaciente(any(PacienteCreateDTO.class));
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar409QuandoPacienteJaExiste() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Carlos da Silva", "29357232443", dataNasc, "Masculino",
                "81 999999999", false, true, endereco, 1288);

        doThrow(new PacienteException("Paciente já cadastrado(a)"))
                .when(enfermagemService).criarPaciente(any(PacienteCreateDTO.class));

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Paciente já cadastrado(a)"));

        verify(enfermagemService, times(1)).criarPaciente(any(PacienteCreateDTO.class));
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoNomeForVazio() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("", "29357232443", dataNasc, "Masculino",
                "81 999999999", false, true, endereco, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoCpfForVazio() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Carlos da Silva", "", dataNasc, "Masculino",
                "81 999999999", false, true, endereco, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoDataNascForVazia() throws Exception {
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Maria da Silva", "29357232443", null, "Masculino",
                "81 999999999", false, true, endereco, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoSexoForVazio() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Maria da Silva", "29357232443", dataNasc, "",
                "81 999999999", false, true, endereco, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoTelefoneForVazio() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Maria da Silva", "29357232443", dataNasc, "Masculino",
                "", false, true, endereco, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoHtvl1ForVazio() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Maria da Silva", "29357232443", dataNasc, "Masculino",
                "81 999999999", null, true, endereco, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoHtvl2ForVazio() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Maria da Silva", "29357232443", dataNasc, "Masculino",
                "81 999999999", false, null, endereco, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoEnderecoForVazio() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Maria da Silva", "29357232443", dataNasc, "Masculino",
                "81 999999999", false, true, null, 1288);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }

    @Test
    @WithMockUser(roles = {"ENFERMAGEM"})
    void deveRetornar400QuandoNumProntuarioForVazio() throws Exception {
        LocalDate dataNasc = LocalDate.of(2001, 6, 8);
        Endereco endereco = new Endereco("Rua X", "123", "Bairro X", "Cidade X", "PE",
                "50000-000", "Casa");
        PacienteCreateDTO pacienteCreateDTO = new PacienteCreateDTO("Maria da Silva", "29357232443", dataNasc, "Masculino",
                "81 999999999", false, true, endereco, null);

        mockMvc.perform(post("/api/enfermagem/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteCreateDTO)))
                .andExpect(status().isBadRequest());

        verify(enfermagemService, never()).criarPaciente(any());
    }
}
