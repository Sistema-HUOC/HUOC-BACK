package br.edu.upe.huocbackend.repository;

import br.edu.upe.huocbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface IUserRepository extends JpaRepository<User, UUID>, RevisionRepository<User,UUID,Integer> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.active = :active WHERE u.email = :email")
    void updateUserActiveByEmail(@Param("active") boolean active, @Param("email") String email);

}
