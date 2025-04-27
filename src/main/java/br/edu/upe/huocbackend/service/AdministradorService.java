package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.exception.AdministradirException;
import br.edu.upe.huocbackend.model.AcessLevel;
import br.edu.upe.huocbackend.model.Administrador;
import br.edu.upe.huocbackend.repository.IAdministradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    private final IAdministradorRepository administradorRepository ;

    public AdministradorService(IAdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public Administrador findByEmail(String email) {
        return administradorRepository.findByEmail(email).orElseThrow(() -> new AdministradirException("Adiministrador não encontrado"));
    }

    @Transactional
    public void save(AdministradorCreateDto administrador) {
        Boolean admin = administradorRepository.existsByEmail(administrador.email);
        if(admin) {
            throw new AdministradirException("Administrador já cadastrado");
        }
        administradorRepository.save(new Administrador(administrador.nome,administrador.cpf,
                administrador.email,administrador.password, AcessLevel.ADMINISTRATOR));

    }
}
