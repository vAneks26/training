package org.example.Stream.Ter;

import java.util.Arrays;
import java.util.List;

public class Count1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        //Подсчитывает количество элементов.
        long count = words.stream().count();
        System.out.println(count);
    }
}
