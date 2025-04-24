package br.edu.upe.huocbackend.repository;


import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFormularioSintomatologiaRepository extends JpaRepository<FormularioSintomatologia, UUID>, RevisionRepository<FormularioSintomatologia,UUID,Integer> {
}
