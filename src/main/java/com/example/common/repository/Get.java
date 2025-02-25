package com.example.common.repository;

import com.example.common.BaseEntity;

public interface Get<T extends BaseEntity, ID> extends Data<T> {
    default T get(ID id){
        return getData().stream()
                .filter(e->e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No data found")
        );
    }
    
}
