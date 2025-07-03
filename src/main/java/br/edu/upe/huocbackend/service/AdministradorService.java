package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.controller.dto.administrador.ResponseAdminastradorDTO;
import br.edu.upe.huocbackend.controller.dto.administrador.UpdateAdministradorDto;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.controller.dto.enfermagem.ResponseEnfermeiroDTO;
import br.edu.upe.huocbackend.controller.dto.enfermagem.UpdateEnfermeiroDTO;
import br.edu.upe.huocbackend.controller.dto.medico.CreateEspecializacaoDTO;
import br.edu.upe.huocbackend.controller.dto.medico.MedicoCriacaoDTO;
import br.edu.upe.huocbackend.controller.dto.medico.ResponseMedicosDTO;
import br.edu.upe.huocbackend.controller.dto.medico.UpdateMedicoDto;
import br.edu.upe.huocbackend.controller.dto.pesquisador.PesquisadorCreateDto;
import br.edu.upe.huocbackend.controller.dto.pesquisador.UpdatePesquisadorDto;
import br.edu.upe.huocbackend.exception.*;
import br.edu.upe.huocbackend.model.*;
import br.edu.upe.huocbackend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

    private final IAdministradorRepository administradorRepository;
    private final IEnfermagemRepository enfermagemRepository;
    private final IPesquisadorRepository pesquisadorRepository;
    private final IInstituicaoRepository instituicaoRepository;
    private final IAreaAtuacaoRepository areaAtuacaoRepository;
    private final IMedicoRepository medicoRepository;
    private final IEspecializacaoRepository especializacaoRepository;
    private final PasswordEncoder passwordEncoder;

    public AdministradorService(
            IAdministradorRepository administradorRepository, IEnfermagemRepository enfermagemRepository,
            IPesquisadorRepository pesquisadorRepository, IInstituicaoRepository instituicaoRepository,
            IAreaAtuacaoRepository areaAtuacaoRepository,IMedicoRepository medicoRepository,
            IEspecializacaoRepository especializacaoRepository,PasswordEncoder passwordEncoder) {
        this.administradorRepository = administradorRepository;
        this.enfermagemRepository = enfermagemRepository;
        this.pesquisadorRepository = pesquisadorRepository;
        this.instituicaoRepository = instituicaoRepository;
        this.areaAtuacaoRepository = areaAtuacaoRepository;
        this.especializacaoRepository = especializacaoRepository;
        this.medicoRepository = medicoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Administrador findByEmail(String email) {
        return administradorRepository.findByEmail(email).orElseThrow(() -> new AdministradorException("Adiministrador não encontrado"));
    }

    @Transactional
    public void save(AdministradorCreateDto administrador) {
        if(administradorRepository.existsByEmail(administrador.email)) {
            throw new AdministradorException("Administrador já cadastrado");
        }
        administradorRepository.save(new Administrador(administrador.nome,administrador.cpf,
                administrador.email, passwordEncoder.encode(administrador.password), AcessLevel.ADMINISTRADOR));
    }

    @Transactional
    public void adminCreateMedico(MedicoCriacaoDTO dto){
        if (medicoRepository.existsByEmail(dto.email)) {
            throw new MedicoException("Médico(a) com o e-mail '" + dto.email + "' já está cadastrado(a).");
        }

        List<Especializacao> especializacaoList = especializacaoRepository.findAll();

        Set<Especializacao> filtradas = especializacaoList.stream()
                .filter(espec -> dto.especializacoes.contains(espec.getTipoEspecializacao()))
                .collect(Collectors.toSet());
        if(filtradas.isEmpty()){
            throw new EspecializacaoException("Medico tem que ter pelo menos uma especialização valida!");
        }
        medicoRepository.save(new Medico(dto.nome,dto.cpf,dto.email,passwordEncoder.encode(dto.password),AcessLevel.MEDICO,dto.crm,filtradas));
    }

    @Transactional
    public void adminCreateEspecializacao(CreateEspecializacaoDTO dto){
        if(especializacaoRepository.existsByTipoEspecializacao(dto.especializacao())) {
            throw new EspecializacaoException("Especialização Já cadastrada!");
        }
        especializacaoRepository.save(new Especializacao(dto.especializacao()));
    }

    @Transactional
    public void adminCreateEnfermagem(EnfermagemCreateDTO enfermagem) {
        if( enfermagemRepository.existsByEmail(enfermagem.email)) {
            throw new EnfermagemException("Enfermeiro(a) já cadastrado(a)");
        }
        enfermagemRepository.save(new Enfermagem(enfermagem.nome,enfermagem.cpf,
                enfermagem.email, passwordEncoder.encode(enfermagem.password), AcessLevel.ENFERMAGEM, enfermagem.coren));
    }

    @Transactional
    public void adminCreatePesquisador(PesquisadorCreateDto pesquisador) {
        if(pesquisadorRepository.existsByEmail(pesquisador.email)) {
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

    public Page<ResponseAdminastradorDTO> listAllAdministrador(String nome, String email, boolean ativo, Pageable pageable) {
        return administradorRepository
                .listComParametros(nome, email, ativo, pageable)
                .map(ResponseAdminastradorDTO::new);
    }


    public Page<ResponseEnfermeiroDTO> listAllEnfermeiros(String nome, String email, String coren, boolean ativo, Pageable pageable) {
        return enfermagemRepository
                .listComParametros(nome,email,coren,ativo,pageable)
                .map(ResponseEnfermeiroDTO::new);
    }

    public Page<ResponseMedicosDTO> listAllMedicos(String nome, String email, String crm,String especializacao, boolean ativo, Pageable pageable) {
        return medicoRepository
                .listComParametros(nome,email,crm,especializacao,ativo,pageable)
                .map(ResponseMedicosDTO::new);
    }

    public List<String> listEspecializacoes(){
        return  especializacaoRepository.findAll().stream().map(Especializacao::getTipoEspecializacao).toList();
    }

    @Transactional
    public void updateMedico(UpdateMedicoDto dto) {
        Medico medico = medicoRepository.findById(dto.huodIdentify())
                .orElseThrow(() -> new MedicoException("Médico(a) com ID '" + dto.huodIdentify() + "' não encontrado(a)."));

        medico.setNome(dto.nome());
        medico.setCrm(dto.crm());
        medico.setEmail(dto.email());
        medico.setCpf(dto.cpf());

        Set<Especializacao> especializacoes = buscarEspecializacoesIndividuais(dto.especializacoes());
        medico.setEspecializacoes(especializacoes);

        medicoRepository.save(medico);
    }

    private Set<Especializacao> buscarEspecializacoesIndividuais(Set<String> nomes) {
        Set<Especializacao> especializacoes = new HashSet<>();

        for (String nome : nomes) {
            Especializacao especializacao = especializacaoRepository.findByTipoEspecializacao(nome)
                    .orElseThrow(() -> new EspecializacaoException("Especialização não encontrada: " + nome));
            especializacoes.add(especializacao);
        }

        return especializacoes;
    }

    @Transactional
    public void updatePesquisador(@Valid UpdatePesquisadorDto dto) {
        Pesquisador pesquisador = pesquisadorRepository.findById(dto.huodIdentify())
                .orElseThrow(() -> new PesquisadorException("Pesquisador(a) com ID '" + dto.huodIdentify() + "' não encontrado(a)."));

        pesquisadorRepository.findByEmail(dto.email()).ifPresent(outro -> {
            if (!outro.getId().equals(dto.huodIdentify())) {
                throw new PesquisadorException("E-mail '" + dto.email() + "' já está sendo utilizado por outro(a) pesquisador(a).");
            }
        });

        pesquisador.setNome(dto.nome());
        pesquisador.setCpf(dto.cpf());
        pesquisador.setEmail(dto.email());
        pesquisador.setInstituicao(instituicaoRepository
                .findById(dto.huodIdentify())
                .orElseThrow(()-> new InstituicaoException("Instituição não encontrada com o ID: " + dto.idInstituicao())));
        pesquisador.setAreasAtuacao(areasAtuacaoList(dto.idAreasAtuacao()));
        pesquisadorRepository.save(pesquisador);
    }

    private List<AreaAtuacao> areasAtuacaoList(List<UUID> uuids) {
        List<AreaAtuacao> areas = new ArrayList<>();

        for (UUID id : uuids) {
            AreaAtuacao area = areaAtuacaoRepository.findById(id)
                    .orElseThrow(() -> new AreaAtuacaoException("Área de atuação não encontrada: " + id));
            areas.add(area);
        }

        return areas;
    }

    public void updateAdministrador(@Valid UpdateAdministradorDto dto) {
        Administrador administrador =
                administradorRepository.findById(dto.huodIdentify())
                        .orElseThrow(() -> new AdministradorException(""));

        administradorRepository.findByEmail(dto.email()).ifPresent(outro -> {
            if (!outro.getId().equals(dto.huodIdentify())) {
                throw new AdministradorException("E-mail '" + dto.email() + "' já está sendo utilizado por outro(a) Administrador(a).");
            }
        });
        administrador.setNome(dto.nome());
        administrador.setCpf(dto.cpf());
        administrador.setEmail(dto.email());
        administradorRepository.save(administrador);
    }

    @Transactional
    public void updateEnfermagem(@Valid UpdateEnfermeiroDTO dto) {
        Enfermagem enfermagem = enfermagemRepository.findById(dto.huodIdentify())
                .orElseThrow(() -> new EnfermagemException("Enfermeiro(a) com ID '" + dto.huodIdentify() + "' não encontrado(a)."));

        enfermagemRepository.findByEmail(dto.email()).ifPresent(outro -> {
            if (!outro.getId().equals(dto.huodIdentify())) {
                throw new EnfermagemException("E-mail '" + dto.email() + "' já está sendo utilizado por outro(a) enfermeiro(a).");
            }
        });

        enfermagem.setNome(dto.nome());
        enfermagem.setCpf(dto.cpf());
        enfermagem.setEmail(dto.email());
        enfermagem.setCoren(dto.coren());

        enfermagemRepository.save(enfermagem);
    }

}

