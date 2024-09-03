package org.example.Collection.Class;

import java.util.ArrayList;
import java.util.List;

public class ArrayList1 {
    public static void main(String[] args) {
        // Создаем array list
        List<String> list = new ArrayList<>();
// Добавляем элементы в array list
        list.add("qa");
        list.add("devops");
        list.add("dev");
// Печатаем array list
        System.out.println("Array list: " + list);
// Доступ к элементу по определенному индексу
        String element = list.get(1);
        System.out.println("Element at index 1: " + element);
// Удаление элемента из the array list
        list.remove(1);
// Print the updated array list
        System.out.println("Array list: " + list);
    }
}
