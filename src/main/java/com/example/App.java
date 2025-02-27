package com.example;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.function.*;

import com.example.common.repository.Remove;
import com.example.customexceptions.CustomException;
import com.example.domain.Ingredient;
import com.example.domain.Pizza;
import com.example.generic.A;
import com.example.generic.Session;
import com.example.infraestructura.RepositoryPizza;;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

       CustomException instance = createInstance(CustomException.class); 
       

       Integer z =10;
       Class<?> cl = String.class;             
       printType(String.class);
       printType(Integer.class);
       printType(z.getClass());
       printType(cl);
       

        //Inferencia de tipos
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.stream().filter(null);
        Session.add("a", list);
        var result = Session.<List<String>>getData("a").orElseThrow();
        for(var item:result){
            System.out.println(item);
        }
        
        var a = new A<Integer>();
        double b=30;
        a.add(null);
        a.add("");
        a.add(1);
        a.add(2.0);
        a.<Double>add(b);
        App.<String>printType(String.class);

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

        Remove<Pizza,UUID> remove = new RepositoryPizza();
        var p = remove.get(null);
        remove.remove(p);

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
    static <T> void printType(Class<T> cl){       
        System.out.println(cl.getName());
    }

    public static <T> T createInstance(Class<T> clazz, Object... args) {
        try {
            // Obtener los tipos de los parámetros
            Class<?>[] paramTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }            
            // Obtener el constructor adecuado
            Constructor<T> constructor = clazz.getConstructor(paramTypes);
            
            // Crear la instancia
            return constructor.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la instancia de " + clazz.getName(), e);
        }
    }
}
