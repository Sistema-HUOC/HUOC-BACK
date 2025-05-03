package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.exception.EnfermagemException;
import br.edu.upe.huocbackend.service.AdministradorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AdministradorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class AdministradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AdministradorService administradorService;

    @Test
    void deveCriarEnfermeiroComSucesso() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "12345678900", "maria@email.com", "senha123", "COREN123");

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(administradorService, times(1)).adminCreateEnfermagem(any(EnfermagemCreateDTO.class));
    }

    @Test
    void deveRetornar400QuandoEmailForVazio() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "12345678900", "", "senha123", "COREN123");

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("O e-mail é obrigatório"));

        verify(administradorService, never()).adminCreateEnfermagem(any());
    }

    @Test
    void deveRetornar400QuandoSenhaForVazia() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "12345678900", "maria@email.com", "", "COREN123");

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A senha é obrigatória"));

        verify(administradorService, never()).adminCreateEnfermagem(any());
    }

    @Test
    void deveRetornar400QuandoCorenForVazio() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "12345678900", "maria@email.com", "senha123", "");

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("O número do COREN é obrigatório"));

        verify(administradorService, never()).adminCreateEnfermagem(any());
    }

    @Test
    void deveRetornar409QuandoEnfermeiroJaExiste() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "12345678900", "maria@email.com", "senha123", "COREN123");

        doThrow(new EnfermagemException("Enfermeiro(a) já cadastrado(a)"))
                .when(administradorService).adminCreateEnfermagem(any(EnfermagemCreateDTO.class));

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Enfermeiro(a) já cadastrado(a)"));

        verify(administradorService, times(1)).adminCreateEnfermagem(any(EnfermagemCreateDTO.class));
    }
}
