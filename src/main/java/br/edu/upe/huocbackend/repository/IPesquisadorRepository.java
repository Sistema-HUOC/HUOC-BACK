package br.edu.upe.huocbackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import br.edu.upe.huocbackend.model.Pesquisador;

@Repository
public interface IPesquisadorRepository extends JpaRepository<Pesquisador, UUID>, RevisionRepository<Pesquisador,UUID,Integer> {
    Optional<Pesquisador> findByEmail(String email);
    boolean existsByEmail(String email);
    
    Optional<Pesquisador> findById(UUID id);
    Optional<Pesquisador> findByCpf(String cpf);
    boolean existsByCpf(String cpf);

}
