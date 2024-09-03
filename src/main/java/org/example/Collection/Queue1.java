package org.example.Collection;

import java.util.LinkedList;
import java.util.Queue;

public class Queue1 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

// Добавление элементов в очередь
        queue.add("apple");
        queue.add("banana");
        queue.add("orange");
// Печатаем очередь
        System.out.println("Queue: " + queue);
// Удаляем элемент из очереди
        String element = queue.remove();
        System.out.println("Removed element: " + element);
// Печатаем обновленную очередь
        System.out.println("Queue: " + queue);
    }
}
