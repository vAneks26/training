package org.example.Stream.Ter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ForEach2 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "apricot", "banana", "cherry", "date", "avocado", "blueberry");

        Map<Character, Long> groupedAndCounted = words.stream()
                .collect(Collectors.groupingBy(word -> word.charAt(0), Collectors.counting()));

        groupedAndCounted.forEach((initial, count) -> System.out.println(initial + ": " + count));
    }
}
