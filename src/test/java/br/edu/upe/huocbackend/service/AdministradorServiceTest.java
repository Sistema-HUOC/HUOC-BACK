package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.controller.dto.pesquisador.PesquisadorCreateDto;
import br.edu.upe.huocbackend.exception.AdministradorException;
import br.edu.upe.huocbackend.exception.EnfermagemException;
import br.edu.upe.huocbackend.exception.PesquisadorException;
import br.edu.upe.huocbackend.model.AcessLevel;
import br.edu.upe.huocbackend.model.Administrador;
import br.edu.upe.huocbackend.model.AreaAtuacao;
import br.edu.upe.huocbackend.model.Instituicao;
import br.edu.upe.huocbackend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdministradorServiceTest {

    @Mock
    private IAdministradorRepository administradorRepository;

    @Mock
    private IEnfermagemRepository enfermagemRepository;

    @InjectMocks
    private AdministradorService administradorService;

    private Administrador administrador1;

    @Mock
    private IPesquisadorRepository pesquisadorRepository;
    @Mock
    private IInstituicaoRepository instituicaoRepository;
    @Mock
    private IAreaAtuacaoRepository areaAtuacaoRepository;
    @Mock
    private IMedicoRepository medicoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;



    @BeforeEach
    public void criaAdministrador() {
        administrador1 = new Administrador();
        administrador1.setNome("Administrador1");
        administrador1.setCpf("123456789");
        administrador1.setEmail("administrador1@email.com");
        administrador1.setPassword("123");
        administrador1.setAcessLevel(AcessLevel.ADMINISTRADOR);
    }

    @Test
    void findByEmail() {
        // Arrange
        when(administradorRepository.findByEmail(administrador1.getEmail()))
                .thenReturn(Optional.of(administrador1));

        // Act
        Administrador result = administradorService.findByEmail(administrador1.getEmail());

        // Assert
        assertEquals(administrador1, result);
    }

    @Test
    void findByEmailNotFound() {
        // Arrange
        when(administradorRepository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        // Assert
        assertThrows(AdministradorException.class, () -> {
            administradorService.findByEmail("naoexiste@email.com");
        });
    }

    @Test
    void save() {
        // Arrange
        when(administradorRepository.existsByEmail(administrador1.getEmail()))
                .thenReturn(false);

        // Act
        administradorService.save(new AdministradorCreateDto(
                administrador1.getNome(),
                administrador1.getCpf(),
                administrador1.getEmail(),
                administrador1.getPassword()
        ));

        // Assert
        verify(administradorRepository, times(1)).save(any(Administrador.class));
    }

    @Test
    void saveAdministradorJaExistente() {
        // Arrange
        when(administradorRepository.existsByEmail(administrador1.getEmail()))
                .thenReturn(true);

        // Assert
        assertThrows(AdministradorException.class, () -> {
            administradorService.save(new AdministradorCreateDto(
                    administrador1.getNome(),
                    administrador1.getCpf(),
                    administrador1.getEmail(),
                    administrador1.getPassword()
            ));
        });

        verify(administradorRepository, never()).save(any(Administrador.class));
    }

    @Test
    void deveCadastrarEnfermagemComSucesso() {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Maria", "12345678900", "maria@email.com", "senha123", "COREN123");
        when(enfermagemRepository.existsByEmail(dto.email)).thenReturn(false);

        administradorService.adminCreateEnfermagem(dto);

        verify(enfermagemRepository).save(argThat(enf ->
                enf.getNome().equals(dto.nome) &&
                        enf.getCpf().equals(dto.cpf) &&
                        enf.getEmail().equals(dto.email) &&
                        enf.getCoren().equals(dto.coren) &&
                        enf.getAcessLevel() == AcessLevel.ENFERMAGEM
        ));
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        EnfermagemCreateDTO dto = new EnfermagemCreateDTO("Ana", "98765432100", "ana@email.com", "senha321", "COREN456");
        when(enfermagemRepository.existsByEmail(dto.email)).thenReturn(true);

        assertThrows(EnfermagemException.class, () -> administradorService.adminCreateEnfermagem(dto));
        verify(enfermagemRepository, never()).save(any());
    }


    @Test
    void deveLancarExcecaoQuandoPesquisadorJaExiste() {
        PesquisadorCreateDto dto = new PesquisadorCreateDto("Lucas", "12345678900", "lucas@email.com", "senha123", UUID.randomUUID(), List.of());
        when(pesquisadorRepository.existsByEmail(dto.email)).thenReturn(true);

        assertThrows(PesquisadorException.class, () -> administradorService.adminCreatePesquisador(dto));
        verify(pesquisadorRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoInstituicaoNaoExiste() {
        UUID idInstituicao = UUID.randomUUID();
        PesquisadorCreateDto dto = new PesquisadorCreateDto("Lucas", "12345678900", "lucas@email.com", "senha123", idInstituicao, List.of());

        when(pesquisadorRepository.existsByEmail(dto.email)).thenReturn(false);
        when(instituicaoRepository.findById(idInstituicao)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> administradorService.adminCreatePesquisador(dto));
        verify(pesquisadorRepository, never()).save(any());
    }

    @Test
    void deveSalvarPesquisadorComSucesso() {
        UUID idInstituicao = UUID.randomUUID();
        UUID idArea1 = UUID.randomUUID();
        UUID idArea2 = UUID.randomUUID();

        PesquisadorCreateDto dto = new PesquisadorCreateDto("Lucas", "12345678900", "lucas@email.com", "senha123", idInstituicao, List.of(idArea1, idArea2));

        Instituicao instituicao = new Instituicao();
        instituicao.setId(idInstituicao);
        instituicao.setNomeInstituicao("UFPE");

        AreaAtuacao area1 = new AreaAtuacao();
        area1.setId(idArea1);
        area1.setNomeArea("Doenças Raras");

        AreaAtuacao area2 = new AreaAtuacao();
        area2.setId(idArea2);
        area2.setNomeArea("Epidemiologia");

        when(pesquisadorRepository.existsByEmail(dto.email)).thenReturn(false);
        when(instituicaoRepository.findById(idInstituicao)).thenReturn(Optional.of(instituicao));
        when(areaAtuacaoRepository.findAllById(List.of(idArea1, idArea2))).thenReturn(List.of(area1, area2));
        when(passwordEncoder.encode(dto.password)).thenReturn("senha-hash");

        administradorService.adminCreatePesquisador(dto);

        verify(pesquisadorRepository).save(argThat(p ->
                p.getNome().equals("Lucas") &&
                        p.getCpf().equals("12345678900") &&
                        p.getEmail().equals("lucas@email.com") &&
                        p.getPassword().equals("senha-hash") &&
                        p.getAcessLevel().equals(AcessLevel.PESQUISADOR) &&
                        p.getInstituicao().equals(instituicao) &&
                        p.getAreasAtuacao().containsAll(List.of(area1, area2))
        ));
    }

    @Test
    void deveLancarExcecaoQuandoAreasNaoSaoEncontradas() {
        UUID idInstituicao = UUID.randomUUID();
        UUID idArea1 = UUID.randomUUID();
        UUID idArea2 = UUID.randomUUID();

        PesquisadorCreateDto dto = new PesquisadorCreateDto("Lucas", "12345678900", "lucas@email.com", "senha123", idInstituicao, List.of(idArea1, idArea2));

        Instituicao instituicao = new Instituicao();
        instituicao.setId(idInstituicao);
        instituicao.setNomeInstituicao("UFPE");

        AreaAtuacao area1 = new AreaAtuacao();
        area1.setId(idArea1);
        area1.setNomeArea("Doenças Raras");

        when(pesquisadorRepository.existsByEmail(dto.email)).thenReturn(false);
        when(instituicaoRepository.findById(idInstituicao)).thenReturn(Optional.of(instituicao));
        when(areaAtuacaoRepository.findAllById(List.of(idArea1, idArea2))).thenReturn(List.of(area1));

        assertThrows(EntityNotFoundException.class, () -> administradorService.adminCreatePesquisador(dto));
        verify(pesquisadorRepository, never()).save(any());
    }

}
