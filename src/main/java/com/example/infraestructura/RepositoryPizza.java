package com.example.infraestructura;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.common.repository.Repository;
import com.example.domain.Pizza;

public class RepositoryPizza implements Repository<Pizza,UUID> {
    private static final Set<Pizza> data = new HashSet<>();
    @Override
    public Set<Pizza> getData() {
       return data;
    }
    
}
