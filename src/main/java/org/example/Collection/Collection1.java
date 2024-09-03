package org.example.Collection;

import java.util.ArrayList;
import java.util.Collection;

public class Collection1 {
    public static void main(String[] args) {
        // Создаем новую коллекцию
        Collection<String> stringCollection = new ArrayList<>();

        // Добавляем несколько элементов в коллекцию
        stringCollection.add("hello");
        stringCollection.add("world");
        stringCollection.add("foo");
        stringCollection.add("bar");

        // Печатаем число элементов в коллекции
        System.out.println("Number of elements: " + stringCollection.size());

        // Удаляем элемент из коллекции
        stringCollection.remove("foo");

        // Опять печатаем число элементов в коллекции
        System.out.println("Number of elements: " + stringCollection.size());
    }
}
