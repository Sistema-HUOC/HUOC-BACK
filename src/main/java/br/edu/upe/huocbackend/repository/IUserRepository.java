package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface IUserRepository extends JpaRepository<User, UUID>, RevisionRepository<User,UUID,Integer> {
    Optional<User> findByEmail(String email);
}
