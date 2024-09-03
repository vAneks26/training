package org.example.Stream.Ter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindFirst1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        //Возвращает первый элемент потока.
        Optional<String> firstWord = words.stream().findFirst();
        System.out.println(firstWord);
    }
}
