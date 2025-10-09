package tech.core.common.models;

public interface IAuditableListeners {
    void beforeAnyUpdate(Auditable auditable);

    void afterAnyUpdate(Auditable auditable);

    void afterLoad(Auditable auditable);

    void beforeRemove(Auditable auditable);

    void afterRemove(Auditable auditable);
}
