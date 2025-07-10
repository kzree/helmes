package com.kzree.backend.common.domain;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.ZonedDateTime;

public class AuditEntityListener {
    @PrePersist
    public void prePersist(BaseEntity entity) {
        var now = ZonedDateTime.now();
        entity.setCreatedAt(now);
        entity.setModifiedAt(now);
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setModifiedAt(ZonedDateTime.now());
    }
}
