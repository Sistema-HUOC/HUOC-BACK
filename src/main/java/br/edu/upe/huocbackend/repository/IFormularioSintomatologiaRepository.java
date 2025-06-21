package br.edu.upe.huocbackend.repository;


import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import br.edu.upe.huocbackend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IFormularioSintomatologiaRepository extends JpaRepository<FormularioSintomatologia, UUID>, RevisionRepository<FormularioSintomatologia, UUID, Integer> {
    Optional<FormularioSintomatologia> findById(UUID id);
    boolean existsByPaciente(Paciente paciente);
    List<FormularioSintomatologia> findAllByPacienteId(UUID pacienteId);
}
