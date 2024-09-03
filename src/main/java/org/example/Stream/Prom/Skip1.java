package org.example.Stream.Prom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Skip1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> skippedNumbers = numbers.stream()
                //Пропускает первые n элементов.
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(skippedNumbers);
    }
}
