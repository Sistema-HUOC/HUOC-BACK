package br.edu.upe.huocbackend.config.audit;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Audit")
@RevisionEntity(AuditListener.class)
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity extends DefaultRevisionEntity {
    private String modifiedBy;

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
