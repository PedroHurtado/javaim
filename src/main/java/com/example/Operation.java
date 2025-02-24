package com.example;

@FunctionalInterface
public interface Operation<T> {    
    T apply(T a,T b);
} 


//List<Operation<Integer>> operations = new ArrayList<>();
//https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
/*
 * 
 * recorrer la lista e invocarla con los valores 2 y 2
 * for (var operation : operations) {
            System.out.println(operation.apply(2, 2));
 }
 */