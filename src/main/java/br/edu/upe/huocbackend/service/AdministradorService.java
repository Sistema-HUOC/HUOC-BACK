package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.controller.dto.pesquisador.PesquisadorCreateDto;
import br.edu.upe.huocbackend.exception.EnfermagemException;
import br.edu.upe.huocbackend.exception.AdministradorException;
import br.edu.upe.huocbackend.exception.EnfermagemException;
import br.edu.upe.huocbackend.exception.PesquisadorException;
import br.edu.upe.huocbackend.model.AcessLevel;
import br.edu.upe.huocbackend.model.Administrador;
import br.edu.upe.huocbackend.model.AreaAtuacao;
import br.edu.upe.huocbackend.model.Enfermagem;
import br.edu.upe.huocbackend.model.Instituicao;
import br.edu.upe.huocbackend.model.Pesquisador;
import br.edu.upe.huocbackend.repository.IAdministradorRepository;
import br.edu.upe.huocbackend.repository.IAreaAtuacaoRepository;
import br.edu.upe.huocbackend.repository.IEnfermagemRepository;
import br.edu.upe.huocbackend.repository.IInstituicaoRepository;
import br.edu.upe.huocbackend.repository.IPesquisadorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    private final IAdministradorRepository administradorRepository ;
    private final IEnfermagemRepository enfermagemRepository ;
    private final IPesquisadorRepository pesquisadorRepository ;
    private final IInstituicaoRepository instituicaoRepository;
    private final IAreaAtuacaoRepository areaAtuacaoRepository ;
    private final PasswordEncoder passwordEncoder;

    public AdministradorService(IAdministradorRepository administradorRepository, IEnfermagemRepository enfermagemRepository, IPesquisadorRepository pesquisadorRepository, IInstituicaoRepository instituicaoRepository, IAreaAtuacaoRepository areaAtuacaoRepository, PasswordEncoder passwordEncoder) {
        this.administradorRepository = administradorRepository;
        this.enfermagemRepository = enfermagemRepository;
        this.pesquisadorRepository = pesquisadorRepository;
        this.instituicaoRepository = instituicaoRepository;
        this.areaAtuacaoRepository = areaAtuacaoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Administrador findByEmail(String email) {
        return administradorRepository.findByEmail(email).orElseThrow(() -> new AdministradorException("Adiministrador não encontrado"));
    }

    @Transactional
    public void save(AdministradorCreateDto administrador) {
        Boolean admin = administradorRepository.existsByEmail(administrador.email);
        if(admin) {
            throw new AdministradorException("Administrador já cadastrado");
        }
        administradorRepository.save(new Administrador(administrador.nome,administrador.cpf,
                administrador.email,administrador.password, AcessLevel.ADMINISTRADOR));
    }

    @Transactional
    public void adminCreateEnfermagem(EnfermagemCreateDTO enfermagem) {
        Boolean enfermeiro = enfermagemRepository.existsByEmail(enfermagem.email);
        if(enfermeiro) {
            throw new EnfermagemException("Enfermeiro(a) já cadastrado(a)");
        }
        enfermagemRepository.save(new Enfermagem(enfermagem.nome,enfermagem.cpf,
                enfermagem.email,enfermagem.password, AcessLevel.ENFERMAGEM, enfermagem.coren));
    }

    @Transactional
    public void adminCreatePesquisador(PesquisadorCreateDto pesquisador) {
        Boolean pesquisadorExiste  = pesquisadorRepository.existsByEmail(pesquisador.email);
        if(pesquisadorExiste) {
            throw new PesquisadorException("Pesquisador(a) já cadastrado(a)");
        }

        Instituicao instituicao = instituicaoRepository.findById(pesquisador.getIdInstituicao()).orElseThrow(()
                -> new EntityNotFoundException("Intituição não encontrada"));

        List<AreaAtuacao> areasAtua = areaAtuacaoRepository.findAllById(pesquisador.getIdAreasAtuacao());

        if (areasAtua.size() != pesquisador.getIdAreasAtuacao().size()) {
            throw new EntityNotFoundException("Uma ou mais áreas de atuação são inválidas");
        }

        pesquisadorRepository.save(new Pesquisador(pesquisador.nome,pesquisador.cpf,
                pesquisador.email, passwordEncoder.encode(pesquisador.password) , AcessLevel.PESQUISADOR, instituicao, areasAtua));
    }

    }

