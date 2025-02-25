package com.example.domain;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.example.common.BaseEntity;

public class Pizza extends BaseEntity {
    private static final Double PROFIT = 1.20D;
    private String name;
    private String description;
    private String url;
    private final Set<Ingredient> ingredients;

    protected Pizza(
            UUID id,
            String name,
            String description,
            String url,
            Set<Ingredient> ingredients) {
        super(id);
        this.name = name;
        this.description = description;
        this.url = url;
        this.ingredients = ingredients;
    }
    void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);

    }
    void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }
    void Update(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;

    }
    public String getDescription() {
        return description;
    }
    public List<Ingredient> getIngredients() {
        return ingredients.stream().toList();
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public double getPrice() {
        return ingredients.stream()
                .mapToDouble(Ingredient::getCost)
                .sum() * PROFIT;
    }
    public static Pizza create(
            String name,
            String description,
            String url,
            Set<Ingredient> ingredients) {
        return new Pizza(UUID.randomUUID(), name, description, url, ingredients);
    }

}
