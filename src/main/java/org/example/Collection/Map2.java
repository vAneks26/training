package org.example.Collection;

import java.util.HashMap;
import java.util.Map;

public class Map2 {
    public static void main(String[] args) {
        // Создаем Map
        Map<String, Integer> map = new HashMap<>();

        // Добавляем элементы в Map
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("orange", 3);

        // Печать Map
        System.out.println("Map: " + map);

        // Получаем значение для определенного ключа
        int value = map.get("banana");
        System.out.println("Значение для 'banana': " + value);

        // Удаляем элемент из Map
        map.remove("orange");

        // Печать обновленной Map
        System.out.println("Обновленный Map: " + map);
    }
}