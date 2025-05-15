package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.instituicao.InstituicaoDto;
import br.edu.upe.huocbackend.repository.IInstituicaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InstituicaoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IInstituicaoRepository instituicaoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        instituicaoRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar uma instituição com sucesso")
    void deveCriarInstituicaoComSucesso() throws Exception {
        InstituicaoDto dto = new InstituicaoDto("Instituto Federal");

        mockMvc.perform(post("/api/instituicao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar 400 ao tentar criar instituição com nome vazio")
    void deveRetornar400ComNomeInvalido() throws Exception {
        InstituicaoDto dto = new InstituicaoDto(""); // nome inválido

        mockMvc.perform(post("/api/instituicao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
