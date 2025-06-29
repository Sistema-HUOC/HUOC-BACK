package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IInstituicaoRepository extends JpaRepository<Instituicao, UUID>, RevisionRepository<Instituicao, UUID, Integer> {

    @Query("""
    SELECT i FROM Instituicao i 
    WHERE (:nomeInstituicao IS NULL OR LOWER(i.nomeInstituicao) LIKE LOWER(CONCAT('%', :nomeInstituicao, '%')))
      AND (:nomeCampos IS NULL OR LOWER(i.nomeCampos) LIKE LOWER(CONCAT('%', :nomeCampos, '%')))
      AND (:cnpj IS NULL OR LOWER(i.cnpj) LIKE LOWER(CONCAT('%', :cnpj, '%')))""")
    List<Instituicao> listComParametros(@Param("nomeInstituicao")String nomeInstituicao,
                                        @Param("nomeCampos") String nomeCampos,
                                        @Param("cnpj") String cnpj);

}
