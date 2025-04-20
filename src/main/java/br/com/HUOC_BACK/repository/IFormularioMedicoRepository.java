package br.com.HUOC_BACK.repository;
import br.com.HUOC_BACK.model.FormularioMedico;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import java.util.UUID;

@Repository
public interface IFormularioMedicoRepository extends JpaRepository<FormularioMedico, UUID>, RevisionRepository<FormularioMedico,UUID,Integer> {}
