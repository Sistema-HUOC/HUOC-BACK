package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.Enfermagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEnfermagemRepository extends JpaRepository<Enfermagem, UUID> , RevisionRepository<Enfermagem, UUID, Integer> {
    Optional<Enfermagem> findByEmail(String email);
    boolean existsByEmail(String email);
}
