package br.edu.upe.huocbackend.controller;

import br.edu.upe.huocbackend.controller.dto.areaAtuacao.AreaAtuacaoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class AreaAtuacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveCriarAreaAtuacaoComSucesso() throws Exception {
        AreaAtuacaoDto dto = new AreaAtuacaoDto();
        dto.setNomeArea("Cardiologia");

        mockMvc.perform(post("/api/areas-atuacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveRetornarErroQuandoNomeNaoForInformado() throws Exception {
        AreaAtuacaoDto dto = new AreaAtuacaoDto();

        mockMvc.perform(post("/api/areas-atuacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}