package br.edu.upe.huocbackend.service;


import br.edu.upe.huocbackend.controller.dto.instituicao.CreateInstituicaoDto;
import br.edu.upe.huocbackend.controller.dto.instituicao.ResponseInstituicaoDto;
import br.edu.upe.huocbackend.controller.dto.instituicao.UpdateInstituicaoDto;
import br.edu.upe.huocbackend.exception.InstituicaoException;
import br.edu.upe.huocbackend.model.Instituicao;
import br.edu.upe.huocbackend.repository.IInstituicaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituicaoService {


    private final IInstituicaoRepository instituicaoRepository;

    public InstituicaoService(IInstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    @Transactional
    public void createInstituicao(CreateInstituicaoDto dto) {
        Instituicao instituicao = new Instituicao(dto);

        instituicaoRepository.save(instituicao);
    }

    public List<ResponseInstituicaoDto> listInstituicaos(String nomeInstituicao,
                                                         String nomeDoCampos,
                                                         String cnpj) {
        return instituicaoRepository.listComParametros(nomeInstituicao,nomeDoCampos,cnpj)
                .stream().map(ResponseInstituicaoDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateInstituicao(UpdateInstituicaoDto dto){
        Instituicao instituicao= instituicaoRepository.findById(dto.id())
                .orElseThrow(() -> new InstituicaoException("Instituição não encontrada com o ID: " + dto.id()));

        instituicao.setNomeInstituicao(dto.nomeInstituicao());
        instituicao.setNomeCampos(dto.nomeDoCampos());
        instituicao.setCnpj(dto.cnpj());

        instituicaoRepository.save(instituicao);
    }
}