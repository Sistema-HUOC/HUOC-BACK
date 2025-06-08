package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.config.security.SecurityConfig;
import br.edu.upe.huocbackend.config.security.TokenService;
import br.edu.upe.huocbackend.controller.dto.areaAtuacao.AreaAtuacaoDto;
import br.edu.upe.huocbackend.service.AreaAtuacaoService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import({TokenService.class})
@ImportAutoConfiguration(classes = {SecurityConfig.class})
public class AreaAtuacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AreaAtuacaoService areaAtuacaoService;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void deveCriarAreaAtuacaoComSucesso() throws Exception {
        AreaAtuacaoDto dto = new AreaAtuacaoDto();
        dto.setNomeArea("Cardiologia");

        mockMvc.perform(post("/api/areas-atuacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(areaAtuacaoService, times(1)).CreateAreaAtuacao(any(AreaAtuacaoDto.class));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void deveRetornarErroQuandoNomeNaoForInformado() throws Exception {
        AreaAtuacaoDto dto = new AreaAtuacaoDto(); // nomeArea null

        mockMvc.perform(post("/api/areas-atuacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(areaAtuacaoService, never()).CreateAreaAtuacao(any());
    }
}
