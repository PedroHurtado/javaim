package com.example.common.repository;


import java.util.Set;

import com.example.common.BaseEntity;

public interface Data<T extends BaseEntity> {
    Set<T> getData();
}
