package org.example.Stream.Prom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Per {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 23),
                new Person("Bob", 34),
                new Person("Charlie", 19),
                new Person("David", 40)
        );

        List<String> namesOfAdults = people.stream()
                .filter(person -> person.age >= 21)
                .map(person -> person.name)
                .collect(Collectors.toList());

        System.out.println(namesOfAdults);
    }
}
