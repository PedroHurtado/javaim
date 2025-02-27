package com.example.features.ingredient;

import com.example.common.repository.Add;
import com.example.domain.Ingredient;

public class CreateIngredient {
    private final Add<Ingredient> repository;
    public CreateIngredient(Add<Ingredient> repository){
        this.repository = repository;
    }
    public void hadler(Ingredient ingredient){
        repository.add(ingredient);
    }
}
