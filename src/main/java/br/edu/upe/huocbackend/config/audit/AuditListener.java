package br.edu.upe.huocbackend.config.audit;


import br.edu.upe.huocbackend.model.User;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        AuditEntity auditEntity = (AuditEntity) o;
        auditEntity.setModifiedBy(getCurrentUserId());
    }
    private String getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()){
            return "Unauthenticated";
        }
        User user = (User) auth.getPrincipal();
        return user.getId().toString();
    }
}
