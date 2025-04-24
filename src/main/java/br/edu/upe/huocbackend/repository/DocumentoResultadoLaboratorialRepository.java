package br.edu.upe.huocbackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import br.edu.upe.huocbackend.model.DocumentoResultadoLaboratorial;

@Repository
public interface DocumentoResultadoLaboratorialRepository extends JpaRepository<DocumentoResultadoLaboratorial, UUID>,
		RevisionRepository<DocumentoResultadoLaboratorial, UUID, Integer> {

}
