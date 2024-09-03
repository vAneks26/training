package org.example.Stream.Specialized;

import java.util.stream.DoubleStream;

public class DoubleStream1 {
    public static void main(String[] args) {
        DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0);
        double average = doubleStream.average().orElse(0.0);
        //среднее значение всех эл
        System.out.println(average);

    }
}
