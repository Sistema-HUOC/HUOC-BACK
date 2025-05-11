package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.config.security.SecurityConfig;
import br.edu.upe.huocbackend.config.security.TokenService;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.exception.EnfermagemException;
import br.edu.upe.huocbackend.repository.IUserRepository;
import br.edu.upe.huocbackend.service.AdministradorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AdministradorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Import({TokenService.class})
@ImportAutoConfiguration(classes = {SecurityConfig.class})
class AdministradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    public IUserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AdministradorService administradorService;

    @MockitoBean
    private TokenService tokenServive;


    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveCriarEnfermeiroComSucesso() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "78452338031", "maria@email.com", "senha123", "123456-PE");

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(administradorService, times(1)).adminCreateEnfermagem(any(EnfermagemCreateDTO.class));
    }

    @WithMockUser("ADMINISTRATOR")
    @Test
    void deveRetornar400QuandoEmailForVazio() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "78452338031", "", "senha123", "123456-PE");

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreateEnfermagem(any());
    }

    @WithMockUser("ADMINISTRATOR")
    @Test
    void deveRetornar400QuandoSenhaForVazia() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "78452338031", "maria@email.com", "", "123456-PE");

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreateEnfermagem(any());
    }

    @WithMockUser("ADMINISTRATOR")
    @Test
    void deveRetornar400QuandoCorenForVazio() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "78452338031", "maria@email.com", "senha123", "");

        mockMvc.perform(post("/api/adm/enfermeiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreateEnfermagem(any());
    }

    @WithMockUser("ADMINISTRATOR")
    @Test
    void deveRetornar409QuandoEnfermeiroJaExiste() throws Exception {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "78452338031", "maria@email.com", "senha123", "123456-PE");

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
