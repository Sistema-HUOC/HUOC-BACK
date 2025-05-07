package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, UUID>, RevisionRepository<Paciente, UUID, Integer> {
    Optional<Paciente> findById(UUID id);
}
