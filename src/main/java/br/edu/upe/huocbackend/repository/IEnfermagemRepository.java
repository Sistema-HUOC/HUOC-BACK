package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.Enfermagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEnfermagemRepository extends JpaRepository<Enfermagem, UUID> , RevisionRepository<Enfermagem, UUID, Integer> {
    Optional<Enfermagem> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("""
    SELECT e FROM Enfermagem e 
    WHERE (:nome IS NULL OR LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
      AND (:email IS NULL OR LOWER(e.email) LIKE LOWER(CONCAT('%', :email, '%')))
      AND (:coren IS NULL OR LOWER(e.coren) LIKE LOWER(CONCAT('%', :coren, '%')))
      AND e.active = :active """)
    Page<Enfermagem> listComParametros(@Param("nome")String nome, @Param("email") String email,
                                       @Param("coren") String coren, @Param("active") boolean active,Pageable pageable);
}
