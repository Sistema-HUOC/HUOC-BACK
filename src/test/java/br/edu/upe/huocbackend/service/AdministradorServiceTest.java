package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.exception.AdministradorException;
import br.edu.upe.huocbackend.model.AcessLevel;
import br.edu.upe.huocbackend.model.Administrador;
import br.edu.upe.huocbackend.repository.IAdministradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdministradorServiceTest {

    @Mock
    private IAdministradorRepository administradorRepository;

    @InjectMocks
    private AdministradorService administradorService;

    private Administrador administrador1;

    @BeforeEach
    public void criaAdministrador() {
        administrador1 = new Administrador();
        administrador1.setNome("Administrador1");
        administrador1.setCpf("123456789");
        administrador1.setEmail("administrador1@email.com");
        administrador1.setPassword("123");
        administrador1.setAcessLevel(AcessLevel.ADMINISTRATOR);
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
}
