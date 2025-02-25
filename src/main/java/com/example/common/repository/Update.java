package com.example.common.repository;

import com.example.common.BaseEntity;

public interface Update<T extends BaseEntity, ID> extends  Get<T,ID> {
    default void update(T t){
        getData().remove(t);
        getData().add(t);
    }   
}  
