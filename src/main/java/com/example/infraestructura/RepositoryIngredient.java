package com.example.infraestructura;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.common.repository.Repository;
import com.example.domain.Ingredient;

public class RepositoryIngredient implements Repository<Ingredient,UUID> {
    private static final Set<Ingredient> data = new HashSet<>();
    @Override
    public Set<Ingredient> getData() {
       return data;
    }
    
}
