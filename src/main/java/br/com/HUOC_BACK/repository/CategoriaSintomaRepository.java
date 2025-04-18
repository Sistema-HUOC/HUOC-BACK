package br.com.HUOC_BACK.repository;

import br.com.HUOC_BACK.model.CategoriaSintoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CategoriaSintomaRepository extends JpaRepository<CategoriaSintoma, UUID>, RevisionRepository<CategoriaSintoma,UUID,Integer> {
}

