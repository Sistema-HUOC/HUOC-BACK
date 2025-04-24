package br.edu.upe.huocbackend.repository;
import br.edu.upe.huocbackend.model.CategoriaExame;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import java.util.UUID;

@Repository
public interface ICategoriaExameRepository extends JpaRepository<CategoriaExame, UUID>, RevisionRepository<CategoriaExame,UUID,Integer>{}
