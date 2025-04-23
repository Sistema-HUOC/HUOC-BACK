package br.com.HUOC_BACK.Users.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import br.com.HUOC_BACK.Users.domain.model.DocumentoResultadoLaboratorial;

@Repository
public interface DocumentoResultadoLaboratorialRepository extends JpaRepository<DocumentoResultadoLaboratorial, UUID>,
		RevisionRepository<DocumentoResultadoLaboratorial, UUID, Integer> {

}
