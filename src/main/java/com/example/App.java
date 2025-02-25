package com.example;

//import java.util.ArrayList;
import java.util.HashSet;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.function.*;

import com.example.domain.Ingredient;
import com.example.domain.Pizza;;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        var tomate = Ingredient.create("Tomate", 1.5);
        var queso = Ingredient.create("Queso", 1.2);

        var ingredientes = new HashSet<Ingredient>();
        ingredientes.add(queso);
        ingredientes.add(tomate);

        var pizza = Pizza.create(
                "carbonara",
                "buenisima",
                "img", ingredientes);        
        System.out.println(pizza.getPrice());

        /*
         * Function<Integer,Function<Integer,Integer>> clousure = (a)->{
         * return (b)->a+b;
         * };
         * 
         * print("Hello", (value)->System.out.println(value));
         * print("Hello", System.out::println);
         * 
         * System.out.println(clousure(5).apply(3));
         * 
         * 
         * System.out.println(App.clousure(5).apply(3)); //8
         * 
         * var result = App.clousure(100); //Function<integer,integer>
         * System.out.println(result.apply(3)); //103
         * System.out.println(result.apply(30 )); //130
         * System.out.println(result.apply(40)); //140
         * 
         * //BiFunction<Integer,Integer,Integer> sum = (a,b)->a+b;
         * 
         * 
         * public interface Operation<T> {
         * T apply(T a,T b);
         * }
         * 
         * Operation<Integer> sum = (a,b)->a+b;
         * 
         * List<Operation<Integer>> operations = new ArrayList<>();
         * 
         * 
         * 
         * operations.add(sum);
         * 
         * operations.add((a,b)->{
         * return a*b;
         * });
         * 
         * operations.add(App::divide);
         * 
         * operations.add((a,b)->a-b);
         * 
         * for (Operation<Integer> operation : operations) {
         * System.out.println(operation.apply(2, 2));
         * }
         * 
         * /*try {
         * Acount ac = new Acount();
         * ac.abono(100);
         * ac.cargo(50);
         * //ac.cargo(-10);
         * //ac.abono(-15);
         * } catch (IllegalArgumentException e) {
         * System.out.println( e.getMessage() );
         * }
         * catch (Exception e) {
         * System.out.println( e.getMessage() );
         * }
         */

    }

    static int divide(int a, int b) {
        return a / b;
    }

    static Function<Integer, Integer> clousure(int a) {
        return (b) -> a + b;
    }

    // print("Hello", System.out::println);
    static void print(Object value, Consumer<Object> printer) {
        printer.accept(value);
    }
}
