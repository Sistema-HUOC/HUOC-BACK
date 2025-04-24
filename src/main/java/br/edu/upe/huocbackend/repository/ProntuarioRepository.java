package br.edu.upe.huocbackend.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import br.edu.upe.huocbackend.model.Prontuario;

@Repository
public interface ProntuarioRepository
		extends JpaRepository<Prontuario, UUID>, RevisionRepository<Prontuario, UUID, Integer> {
}
