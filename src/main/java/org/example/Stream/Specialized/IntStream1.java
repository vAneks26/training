package org.example.Stream.Specialized;

import java.util.stream.IntStream;

public class IntStream1 {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        int sum = intStream.sum();
        System.out.println(sum);

    }
}
