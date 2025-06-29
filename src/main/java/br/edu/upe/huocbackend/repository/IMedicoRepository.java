package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.Enfermagem;
import br.edu.upe.huocbackend.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, UUID>, RevisionRepository<Medico,UUID,Integer>{

    boolean existsByEmail(String email);

    @Query("""
    SELECT m FROM Medico m 
    JOIN m.especializacoes e 
    WHERE (:nome IS NULL OR LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
      AND (:email IS NULL OR LOWER(m.email) LIKE LOWER(CONCAT('%', :email, '%')))
      AND (:crm IS NULL OR LOWER(m.crm) LIKE LOWER(CONCAT('%', :crm, '%')))
      AND (:tipoEspecializacao IS NULL OR LOWER(e.tipoEspecializacao) LIKE LOWER(CONCAT('%', :tipoEspecializacao, '%')))
      AND m.active = :active """)
    Page<Medico> listComParametros(@Param("nome")String nome, @Param("email") String email,
                                       @Param("crm") String crm,@Param("tipoEspecializacao") String tipoEspecializacao, @Param("active") boolean active, Pageable pageable);
}
