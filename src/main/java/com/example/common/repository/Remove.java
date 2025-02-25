package com.example.common.repository;

import com.example.common.BaseEntity;

public interface Remove <T extends BaseEntity, ID> extends Get<T,ID> {
    default void remove(T t){
        getData().remove(t);
    }
    
}
