package org.example.Stream.Prom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class distinct1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        List<Integer> distinctNumbers = numbers.stream()
                //Удаляет дублирующиеся элементы.
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctNumbers);

    }
}
