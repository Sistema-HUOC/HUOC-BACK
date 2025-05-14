package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.config.security.SecurityConfig;
import br.edu.upe.huocbackend.config.security.TokenService;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.controller.dto.pesquisador.PesquisadorCreateDto;
import br.edu.upe.huocbackend.exception.EnfermagemException;
import br.edu.upe.huocbackend.exception.PesquisadorException;
import br.edu.upe.huocbackend.model.AreaAtuacao;
import br.edu.upe.huocbackend.repository.IUserRepository;
import br.edu.upe.huocbackend.service.AdministradorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    UUID idInstituicao = UUID.randomUUID();

    List<UUID> idAreasAtuacao = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());



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



    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveCriarPesquisadorComSucesso() throws Exception {
        PesquisadorCreateDto dto = new PesquisadorCreateDto("Carlos", "12345678901", "carlos@email.com", "senha123",  idInstituicao, idAreasAtuacao);

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(administradorService, times(1)).adminCreatePesquisador(any(PesquisadorCreateDto.class));
    }

    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar400QuandoEmailPesquisadorForVazio() throws Exception {
        PesquisadorCreateDto dto = new PesquisadorCreateDto("Carlos", "12345678901", "", "senha123",  idInstituicao, idAreasAtuacao);

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreatePesquisador(any());
    }

    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar400QuandoSenhaPesquisadorForVazia() throws Exception {
        PesquisadorCreateDto dto = new PesquisadorCreateDto("Carlos", "12345678901", "carlos@email.com", "", idInstituicao, idAreasAtuacao);

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreatePesquisador(any());
    }

    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar400QuandoCpfPesquisadorForVazio() throws Exception {
        PesquisadorCreateDto dto = new PesquisadorCreateDto("Carlos", "", "carlos@email.com", "senha123",  idInstituicao, idAreasAtuacao);

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreatePesquisador(any());
    }

    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar409QuandoPesquisadorJaExistir() throws Exception {
        PesquisadorCreateDto dto = new PesquisadorCreateDto("Carlos", "12345678901", "carlos@email.com", "senha123",  idInstituicao, idAreasAtuacao);

        doThrow(new PesquisadorException("Pesquisador(a) já cadastrado(a)"))
                .when(administradorService).adminCreatePesquisador(any(PesquisadorCreateDto.class));

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Pesquisador(a) já cadastrado(a)"));

        verify(administradorService, times(1)).adminCreatePesquisador(any(PesquisadorCreateDto.class));
    }
    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar400QuandoIdInstituicaoForNulo() throws Exception {
        PesquisadorCreateDto dto = new PesquisadorCreateDto(
                "Carlos", "12345678901", "carlos@email.com", "senha123", null, idAreasAtuacao
        );

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreatePesquisador(any());
    }

    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar400QuandoListaDeAreasEstiverVazia() throws Exception {
        PesquisadorCreateDto dto = new PesquisadorCreateDto(
                "Carlos", "12345678901", "carlos@email.com", "senha123", idInstituicao, List.of()
        );

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreatePesquisador(any());
    }


    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar400QuandoIdAreaForInvalido() throws Exception {
        String payload = """
        {
            "nome": "Carlos",
            "cpf": "12345678901",
            "email": "carlos@email.com",
            "senha": "senha123",
            "idInstituicao": "%s",
            "idAreasAtuacao": ["123", "not-a-uuid"]
        }
        """.formatted(idInstituicao);

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreatePesquisador(any());
    }


    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar400QuandoListaDeAreasForNula() throws Exception {
        PesquisadorCreateDto dto = new PesquisadorCreateDto(
                "Carlos", "12345678901", "carlos@email.com", "senha123", idInstituicao, null
        );

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreatePesquisador(any());
    }


    @Test
    @WithMockUser("ADMINISTRATOR")
    void deveRetornar400QuandoIdInstituicaoForInvalido() throws Exception {
        String payload = """
        {
            "nome": "Carlos",
            "cpf": "12345678901",
            "email": "carlos@email.com",
            "senha": "senha123",
            "idInstituicao": "not-a-uuid",
            "idAreasAtuacao": ["%s", "%s"]
        }
        """.formatted(idAreasAtuacao.get(0), idAreasAtuacao.get(1));

        mockMvc.perform(post("/api/adm/pesquisador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());

        verify(administradorService, never()).adminCreatePesquisador(any());
    }


}
