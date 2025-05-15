package br.edu.upe.huocbackend.service;


import br.edu.upe.huocbackend.controller.dto.instituicao.InstituicaoDto;
import br.edu.upe.huocbackend.model.Instituicao;
import br.edu.upe.huocbackend.repository.IInstituicaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstituicaoService {


    private final IInstituicaoRepository instituicaoRepository;

    public InstituicaoService(IInstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    @Transactional
    public void createInstituicao(InstituicaoDto instituicaoDto) {
        Instituicao instituicao = new Instituicao(
                instituicaoDto.getNomeInstituicao());

        instituicaoRepository.save(instituicao);
    }
}