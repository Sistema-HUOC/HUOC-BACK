package br.edu.upe.huocbackend.controller.dto.usuarioIdentificado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.upe.huocbackend.model.Administrador;
import br.edu.upe.huocbackend.model.Enfermagem;
import br.edu.upe.huocbackend.model.Medico;
import br.edu.upe.huocbackend.model.Pesquisador;
import br.edu.upe.huocbackend.model.User;
import br.edu.upe.huocbackend.repository.IAdministradorRepository;
import br.edu.upe.huocbackend.repository.IEnfermagemRepository;
import br.edu.upe.huocbackend.repository.IMedicoRepository;
import br.edu.upe.huocbackend.repository.IPesquisadorRepository;

@Service
public class UsuarioIdentificadorService {

	@Autowired
	private IAdministradorRepository administradorRepo;
    @Autowired private IMedicoRepository medicoRepo;
 //   @Autowired private IPacienteRepository pacienteRepo;
    @Autowired private IEnfermagemRepository enfermagemRepo;
    @Autowired private IPesquisadorRepository pesquisadorRepo;

    public UsuarioIdentificadoDTO identificarPorCpf(String cpf) {
        Optional<Medico> medicoOpt = medicoRepo.findByCpf(cpf);
        if (medicoOpt.isPresent()) {
            return montarDTO(medicoOpt.get(), "Médico");
        }

      ///Optional<Paciente> pacienteOpt = pacienteRepo.findByCpf(cpf);
      ///if (pacienteOpt.isPresent()) {
        	///            return montarDTO(pacienteOpt.get(), "Paciente");
          ///        }

        Optional<Enfermagem> enfermagemOpt = enfermagemRepo.findByCpf(cpf);
        if (enfermagemOpt.isPresent()) {
            return montarDTO(enfermagemOpt.get(), "Enfermagem");
        }

        Optional<Pesquisador> pesquisadorOpt = pesquisadorRepo.findByCpf(cpf);
        if (pesquisadorOpt.isPresent()) {
            return montarDTO(pesquisadorOpt.get(), "Pesquisador");
        }
        Optional<Administrador> administradorOpt = administradorRepo.findByCpf(cpf);
        if (administradorOpt.isPresent()) {
            return montarDTO(administradorOpt.get(), "Administrador");
        }


        return null;
    }


    private UsuarioIdentificadoDTO montarDTO(User user, String tipo) {
        UsuarioIdentificadoDTO dto = new UsuarioIdentificadoDTO();
        dto.setTipo(tipo);
        dto.setNome(user.getNome());
        dto.setCpf(user.getCpf());
        dto.setUserId(user.getId());
        return dto;
    }

}
