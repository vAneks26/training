package org.example.Collection;

import java.util.HashSet;
import java.util.Set;

public class Set1 {public static void main(String[] args) {
    // Создаем новый set
    Set<String> stringSet = new HashSet<>();

    // Добавляем несколько элементов в set
    stringSet.add("Jan");
    stringSet.add("Feb");
    stringSet.add("March");
    stringSet.add("April");

    // Проверяем наличие в set элемента "March"
    if (stringSet.contains("March")) {
        System.out.println("The set contains the element 'March'");
    }

    // Удаляем элемент "April" из set
    stringSet.remove("April");

    // Опять проверяем наличие элемента "April" в set
    if (!stringSet.contains("April")) {
        System.out.println("The set no longer contains the element 'April'");
    }
}
}
