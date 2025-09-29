package tech.core.common.models;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
@NoArgsConstructor
public class BaseEntityListener {

    @Autowired(required = false)
    AuditableListeners auditableListeners;

    private Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("0");
        }
        return Optional.of(authentication.getName());
    }

    @PrePersist // Chạy trước khi INSERT (lưu mới).
    private void beforeAnyInsert(Auditable auditable) {
        Optional<String> currentAuditor = this.getCurrentAuditor();
        auditable.setCreatedBy(Integer.parseInt((String) currentAuditor.get()));
        auditable.setUpdatedBy(Integer.parseInt((String) currentAuditor.get()));
        if (auditable.getCreatedAt() == null) {
            auditable.setCreatedAt(LocalDateTime.now());
        }
        if (auditable.getUpdatedAt() == null) {
            auditable.setUpdatedAt(LocalDateTime.now());
        }
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            auditable.setCreatedProgram(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        } else {
            auditable.setCreatedProgram("System");
        }

        if (auditableListeners != null) {
            auditableListeners.beforeAnyUpdate(auditable);
        }
    }

    @PreUpdate
    private void beforeAnyUpdate(Auditable auditable) {
        Optional<String> currentAuditor = this.getCurrentAuditor();
        auditable.setUpdatedBy(Integer.parseInt((String) currentAuditor.get()));
        auditable.setUpdatedAt(LocalDateTime.now());
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            auditable.setCreatedProgram(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        } else {
            auditable.setCreatedProgram("System");
        }
        if (Objects.nonNull(this.auditableListeners)) {
            this.auditableListeners.beforeAnyUpdate(auditable);
        }
    }

    @PreRemove
    private void beforeAnyRemove(Auditable auditable) {
        if (Objects.nonNull(this.auditableListeners)) {
            this.auditableListeners.beforeAnyUpdate(auditable);
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Auditable auditable) {
        if (Objects.nonNull(this.auditableListeners)) {
            this.auditableListeners.afterAnyUpdate(auditable);
        }
    }

    @PostLoad
    private void afterLoad(Auditable auditable) {
        if (Objects.nonNull(this.auditableListeners)) {
            this.auditableListeners.afterLoad(auditable);
        }
    }
}
