package org.example.Stream.Ter;

import java.util.Arrays;
import java.util.List;

public class ForEach1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        //Выполняет действие над каждым элементом.
        words.forEach(System.out::println);
    }
}
