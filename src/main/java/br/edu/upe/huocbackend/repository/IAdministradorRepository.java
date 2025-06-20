package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.Administrador;
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
public interface IAdministradorRepository extends JpaRepository<Administrador, UUID>, RevisionRepository<Administrador, UUID, Integer> {
    Optional<Administrador> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("""
    SELECT a FROM Administrador a 
    WHERE (:nome IS NULL OR LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
      AND (:email IS NULL OR LOWER(a.email) LIKE LOWER(CONCAT('%', :email, '%')))
      AND a.active = :active """)
    Page<Administrador> listComParametros(
            @Param("nome") String nome,
            @Param("email") String email,
            @Param("active") boolean active,
            Pageable pageable
    );
}
