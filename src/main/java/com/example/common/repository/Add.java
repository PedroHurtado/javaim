package com.example.common.repository;

import com.example.common.BaseEntity;

public interface Add<T extends BaseEntity> extends Data<T> {
    default void add(T entity){
        getData().add(entity);
    }
    
}
