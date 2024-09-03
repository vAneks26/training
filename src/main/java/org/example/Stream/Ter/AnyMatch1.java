package org.example.Stream.Ter;

import java.util.Arrays;
import java.util.List;

public class AnyMatch1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        //есть ли в списке
        boolean hasBanana = words.stream().anyMatch(word -> word.equals("banana"));
        System.out.println(hasBanana);
    }
}
