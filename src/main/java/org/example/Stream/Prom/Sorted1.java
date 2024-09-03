package org.example.Stream.Prom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sorted1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        List<Integer> sortedNumbers = numbers.stream()
                .map(p->p*2)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedNumbers);
    }
}
