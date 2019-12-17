package cl.talca.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsPractice {


    public static void main(String ... args) {

        //functional programming == lambda

        //pipeline of operations (intermediarios (map, filter), finales (to Collect, getFirst))


        List<Integer> list = Arrays.asList(1, 2, 3, 5, 8, 10);


        List<String> result = list.stream()
                .filter(input -> input > 5)
                /*.filter(input -> {
                    return input > 5;
                })*/
                .map(input -> input.toString())
                .peek(input -> {
                    String var = input + "aaa";
                    System.out.println(var);
                })
                /*.peek(System.out::println) //consumer . operation intermedia*/
                .collect(Collectors.toList()); //operation final
/*
        for(String i : result) {
            System.out.println(i);
        }*/

        result.stream().forEach(System.out::println);

    }

}
