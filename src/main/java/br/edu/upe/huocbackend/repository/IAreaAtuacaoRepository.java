package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.AreaAtuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IAreaAtuacaoRepository extends JpaRepository<AreaAtuacao, UUID>, RevisionRepository<AreaAtuacao, UUID, Integer> {

}
