package org.example.Stream.Ter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reduce1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> sum = numbers.stream()
                //сумма всего списка
                .reduce((a, b) -> a + b);
        System.out.println(sum);
    }
}
