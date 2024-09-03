package org.example.Stream.Prom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Filter1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> filteredWords = words.stream()
                .filter(word -> word.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println(filteredWords);
// filteredWords будет содержать ["apple"]

    }
}
