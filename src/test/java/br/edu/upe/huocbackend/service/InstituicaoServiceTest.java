package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.instituicao.InstituicaoDto;
import br.edu.upe.huocbackend.model.Instituicao;
import br.edu.upe.huocbackend.repository.IInstituicaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.mock;

public class InstituicaoServiceTest {
    private IInstituicaoRepository instituicaoRepository;
    private InstituicaoService instituicaoService;

    @BeforeEach
    void setUp() {
        instituicaoRepository = mock(IInstituicaoRepository.class);
        instituicaoService = new InstituicaoService(instituicaoRepository);
    }

    @Test
    @DisplayName("Deve salvar uma nova instituição com o nome correto")
    void deveSalvarInstituicao() {
        // Arrange
        InstituicaoDto dto = new InstituicaoDto("UFPE");

        // Act
        instituicaoService.createInstituicao(dto);

        // Assert
        ArgumentCaptor<Instituicao> captor = ArgumentCaptor.forClass(Instituicao.class);
        verify(instituicaoRepository, times(1)).save(captor.capture());

        Instituicao entidadeSalva = captor.getValue();
        assertThat(entidadeSalva.getNomeInstituicao()).isEqualTo("UFPE");
    }

    @Test
    @DisplayName("Deve lançar exceção se o repositório falhar")
    void deveLancarExcecaoAoSalvarInstituicao() {
        InstituicaoDto dto = new InstituicaoDto("UFPE");

        doThrow(new RuntimeException("Erro no banco"))
                .when(instituicaoRepository)
                .save(any(Instituicao.class));

        assertThatThrownBy(() -> instituicaoService.createInstituicao(dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Erro no banco");
    }



}
