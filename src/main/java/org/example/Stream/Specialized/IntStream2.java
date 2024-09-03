package org.example.Stream.Specialized;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// параллельные потоки для ускорения обработки больших списков чисел.
public class IntStream2 {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.range(1, 10000).boxed().collect(Collectors.toList());

        List<Integer> squares = numbers.parallelStream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println(squares);
    }
}



//.boxed(): Преобразует поток примитивных целых чисел (IntStream) в поток объектов Integer (Stream<Integer>).