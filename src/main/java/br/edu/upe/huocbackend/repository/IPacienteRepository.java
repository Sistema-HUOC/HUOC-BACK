package br.edu.upe.huocbackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import br.edu.upe.huocbackend.model.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, UUID>, RevisionRepository<Paciente, UUID, Integer> {
    Optional<Paciente> findById(UUID id);
    boolean existsByCpf(String cpf);
    
    //Optional<Paciente> findByCpf(String CPF);
    //Boolean existsByCPF(String CPF);

    Optional<Paciente> findByNome(String nome);
    Optional<Paciente> findByCpf(String cpf);
}
