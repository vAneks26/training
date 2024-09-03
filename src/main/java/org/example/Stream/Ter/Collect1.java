package org.example.Stream.Ter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Collect1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        Set<String> wordSet = words.stream()
                //Сбор элементов потока в коллекцию.
                .collect(Collectors.toSet());
        System.out.println(wordSet);
    }
}
