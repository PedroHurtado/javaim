package com.example.features.ingredient;

import java.util.UUID;

import com.example.common.repository.Update;
import com.example.domain.Ingredient;

public class UpdateIngredient {
    private final Update<Ingredient, UUID> repository;
    public UpdateIngredient(Update<Ingredient,UUID> repository){
        this.repository = repository;
    }
    public void handler(Ingredient ingredient){
        Ingredient _ingredient = repository.get(ingredient.getId());
        _ingredient.Update(ingredient.getName(), ingredient.getCost());
        repository.update(_ingredient);
    }
}
