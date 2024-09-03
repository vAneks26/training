package org.example.Stream.Specialized;

import java.util.stream.LongStream;

public class LongStream1 {
    public static void main(String[] args) {
        LongStream longStream = LongStream.range(1, 5);
        long sum = longStream.sum();
        System.out.println(sum);

    }
}
