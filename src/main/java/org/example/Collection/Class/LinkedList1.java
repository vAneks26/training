package org.example.Collection.Class;

import java.util.LinkedList;
import java.util.List;

public class LinkedList1 {
    public static void main(String[] args) {
        // Создаем linked list
        List<String> list = new LinkedList<>();
// Добавляем элементы в linked list
        list.add("selenium");
        list.add("cypress");
        list.add("playwright");
// Печатаем linked list
        System.out.println("Linked list: " + list);
// Добавляем элемент в начало списка
        list.add(0, "webdriver.io");
// Печатаем обновленный linked list
        System.out.println("Linked list: " + list);
// Удаляем первый элемент в списке
        list.remove(0);
// Еще раз печатаем обновленный linked list
        System.out.println("Linked list: " + list);

    }
}
