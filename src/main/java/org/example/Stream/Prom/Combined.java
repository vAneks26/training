package org.example.Stream.Prom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Combined {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("apple", "banana", "cherry");
        List<String> list2 = Arrays.asList("date", "fig", "grape");
        List<String> list3 = Arrays.asList("honeydew", "kiwi", "lime");

        List<String> combinedList = Stream.of(list1, list2, list3)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(combinedList);
    }
}
