package com.example;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicicios {
    public static class A{
        private final UUID id;
        private final String name;
        public A(UUID id,String name){
            this.id = id;
            this.name = name;
        }
        public UUID getId(){
            return id;
        }
        public String getName(){
            return name;
        }

    }
    public static Integer FlatMap(){

        List<List<Integer>> lista = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4,5,6)             
         );
        
        return lista.stream()
                .flatMap(List::stream) 
                .reduce(0, Integer::sum);
    }
    public static Stream<A> geList(){
        List<String> list= Arrays.asList("A","B","C","T");
        return list.stream()
            .map(name -> new A(UUID.randomUUID(), name));
            //Eviten esto en el desarrollo web
            //.collect(Collectors.toList());

        //return result;            
    }

    public static Stream<A> filter(String name){
        return geList().filter(v->
            (name == null || v.getName().contains(name))
        );
    }

    public static Optional<A> filterFirst(String name){
        return geList().filter(v->
            (name == null || v.getName().contains(name))
        ).findFirst();
    }

    public static void join(){
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> commonElements = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());

        System.out.println(commonElements);
    }
}
