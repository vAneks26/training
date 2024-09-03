package org.example.Stream.Prom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Limit1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> limitedNumbers = numbers.stream()
                // Обрезает поток до заданного размера.
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(limitedNumbers);
    }
}
