package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.areaAtuacao.AreaAtuacaoDto;
import br.edu.upe.huocbackend.model.AreaAtuacao;
import br.edu.upe.huocbackend.repository.IAreaAtuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaAtuacaoService {

    private final IAreaAtuacaoRepository areaAtuacaoRepository;

    @Autowired
    public AreaAtuacaoService(IAreaAtuacaoRepository areaAtuacaoRepository) {
        this.areaAtuacaoRepository = areaAtuacaoRepository;
    }

    public void CreateAreaAtuacao(AreaAtuacaoDto areaAtuacaoDto) {
        AreaAtuacao areaAtuacao = new AreaAtuacao(
                areaAtuacaoDto.getNomeArea());
        areaAtuacaoRepository.save(areaAtuacao);
    }

}
