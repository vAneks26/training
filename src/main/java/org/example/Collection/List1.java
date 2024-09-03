package org.example.Collection;

import java.util.ArrayList;
import java.util.List;

public class List1 {
    public static void main(String[] args) {
        // Создаем новый список
        List<String> stringList = new ArrayList<>();

        // Добавляем несколько элементов в список
        stringList.add("India");
        stringList.add("UAE");
        stringList.add("London");
        stringList.add("US");

        // Печатаем первый элемент в списке
        System.out.println("First element: " + stringList.get(0));

        // Удаляем второй элемент из списка
        stringList.remove(1);

        // Печатаем второй элемент в списке
        System.out.println("Second element: " + stringList.get(1));
    }
}
