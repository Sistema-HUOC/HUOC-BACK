package br.com.HUOC_BACK.repository;


import br.com.HUOC_BACK.model.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SintomaRepository extends JpaRepository<Sintoma, UUID>, RevisionRepository<Sintoma,UUID,Integer> {
}

