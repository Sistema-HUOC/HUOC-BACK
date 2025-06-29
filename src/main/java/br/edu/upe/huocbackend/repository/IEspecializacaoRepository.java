package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.Especializacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IEspecializacaoRepository extends JpaRepository<Especializacao, UUID> , RevisionRepository<Especializacao,UUID,Integer> {
    boolean existsByTipoEspecializacao(String tipoEspecializacao);
}
