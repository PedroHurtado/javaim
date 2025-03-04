package com.example.domain;

import java.util.UUID;

import com.example.common.BaseEntity;

public class Ingredient extends BaseEntity{

    private String name;
    private Double cost;
    protected Ingredient(final UUID id ,String name, Double cost){ 
        super(id);
        this.name = name;
        this.cost = cost;
        
    }
    public void Update(String name, Double cost){
        this.name = name;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public Double getCost(){
        return cost;
    }
    public static Ingredient create(String name, Double cost){
        return new Ingredient(UUID.randomUUID(), name, cost);
    }
    
    
}
