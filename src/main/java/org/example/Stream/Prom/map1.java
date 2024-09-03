package org.example.Stream.Prom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class map1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        List<Integer> wordLengths = words.stream()
                //Преобразует элементы потока.
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);


    }
}
