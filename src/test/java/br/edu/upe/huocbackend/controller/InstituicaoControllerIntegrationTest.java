package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.config.security.SecurityConfig;
import br.edu.upe.huocbackend.config.security.TokenService;
import br.edu.upe.huocbackend.controller.dto.instituicao.CreateInstituicaoDto;
import br.edu.upe.huocbackend.service.InstituicaoService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import({TokenService.class})
@ImportAutoConfiguration(classes = {SecurityConfig.class})
public class InstituicaoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private InstituicaoService instituicaoService;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void deveCriarInstituicaoComSucesso() throws Exception {
        CreateInstituicaoDto dto = new CreateInstituicaoDto("UFPE","recife","10.767.239/0001-40");

        mockMvc.perform(post("/api/instituicoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(instituicaoService, times(1)).createInstituicao(any(CreateInstituicaoDto.class));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void deveRetornarErroQuandoNomeForInvalido() throws Exception {
        CreateInstituicaoDto dto = new CreateInstituicaoDto("","recife","10.767.239/0001-40");

        mockMvc.perform(post("/api/instituicoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());


        verify(instituicaoService, never()).createInstituicao(any());
    }
}
