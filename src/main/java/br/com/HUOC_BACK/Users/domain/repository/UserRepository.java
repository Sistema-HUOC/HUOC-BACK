package br.com.HUOC_BACK.Users.domain.repository;

import br.com.HUOC_BACK.Users.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID>, RevisionRepository<User,UUID,Integer> {
    Optional<User> findByEmail(String email);
}
