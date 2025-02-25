package com.example.common.repository;

import java.util.stream.Stream;

import com.example.common.BaseEntity;

public interface Query<T extends BaseEntity> extends Data<T>  {
    default Stream<T> query(){
        return getData().stream();
    }
}
