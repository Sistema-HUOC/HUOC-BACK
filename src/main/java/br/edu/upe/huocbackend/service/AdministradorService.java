package br.edu.upe.huocbackend.service;

import br.edu.upe.huocbackend.controller.dto.administrador.AdministradorCreateDto;
import br.edu.upe.huocbackend.controller.dto.enfermagem.EnfermagemCreateDTO;
import br.edu.upe.huocbackend.exception.AdministradirException;
import br.edu.upe.huocbackend.exception.EnfermagemException;
import br.edu.upe.huocbackend.exception.AdministradorException;
import br.edu.upe.huocbackend.model.AcessLevel;
import br.edu.upe.huocbackend.model.Administrador;
import br.edu.upe.huocbackend.model.Enfermagem;
import br.edu.upe.huocbackend.repository.IAdministradorRepository;
import br.edu.upe.huocbackend.repository.IEnfermagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    private final IAdministradorRepository administradorRepository ;
    private final IEnfermagemRepository enfermagemRepository ;

    public AdministradorService(IAdministradorRepository administradorRepository, IEnfermagemRepository enfermagemRepository) {
        this.administradorRepository = administradorRepository;
        this.enfermagemRepository = enfermagemRepository;
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
                administrador.email,administrador.password, AcessLevel.ADMINISTRATOR));
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

    }

