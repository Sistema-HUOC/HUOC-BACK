package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.areaAtuacao.AreaAtuacaoDto;
import br.edu.upe.huocbackend.model.AreaAtuacao;
import br.edu.upe.huocbackend.repository.IAreaAtuacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AreaAtuacaoServiceTest {


    @Mock
    private IAreaAtuacaoRepository areaAtuacaoRepository;

    @InjectMocks
    private AreaAtuacaoService areaAtuacaoService;

    @Test
    public void deveSalvarAreaAtuacaoCorretamente() {
        AreaAtuacaoDto dto = new AreaAtuacaoDto();
        dto.setNomeArea("Dermatologia");

        areaAtuacaoService.CreateAreaAtuacao(dto);

        ArgumentCaptor<AreaAtuacao> captor = ArgumentCaptor.forClass(AreaAtuacao.class);
        verify(areaAtuacaoRepository, times(1)).save(captor.capture());

        AreaAtuacao entidadeSalva = captor.getValue();
        assertEquals("Dermatologia", entidadeSalva.getNomeArea());
    }
}
