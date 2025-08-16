package br.edu.upe.huocbackend.controller.dto.usuarioIdentificado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.upe.huocbackend.config.audit.AuditEntity;

@Repository
public interface AuditoriaCustomRepository extends JpaRepository<AuditEntity, Integer> {

	@Query(value = """
		    SELECT 
    'paciente' AS entidade,
    LOWER(HEX(pa.id)) AS entidade_id, -- converte UUID binário para string hex
    CASE pa.revtype 
        WHEN 0 THEN 'INSERT'
        WHEN 1 THEN 'UPDATE'
        WHEN 2 THEN 'DELETE'
    END AS acao,
    a.modified_by AS usuario,
    FROM_UNIXTIME(a.timestamp / 1000) AS data_hora
FROM paciente_aud pa
JOIN audit a ON pa.rev = a.id
WHERE a.modified_by = :userId
		""", nativeQuery = true)
		List<Object[]> buscarAuditoriaPaciente(@Param("userId") String userId);
}

