package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, UUID>, RevisionRepository<Medico,UUID,Integer>{
}
