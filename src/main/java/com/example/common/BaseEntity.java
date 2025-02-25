package com.example.common;

import java.util.UUID;

public abstract class BaseEntity {
    private final UUID id;
    protected BaseEntity(final UUID id) {
        this.id = id;
    }
    UUID getId() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) obj;
        return id.equals(that.id);
    }
    @Override
    public int hashCode() {        
        return id.hashCode();
    }
    
}
