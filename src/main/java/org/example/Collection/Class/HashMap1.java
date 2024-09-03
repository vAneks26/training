package org.example.Collection.Class;

import java.util.HashMap;
import java.util.Map;

public class HashMap1 {
    public static void main(String[] args) {
        // Создаем hash map
        Map<String, Integer> map = new HashMap<>();

// Добавляем элементы в hash map
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("orange", 3);
// Печатаем hash map
        System.out.println("Hash map: " + map);
// Получаем значение для определенного ключа
        int value = map.get("banana");
        System.out.println("Value for 'banana': " + value);
// Удаляем элемент из hash map
        map.remove("orange");
// Печатаем обновленный hash map
        System.out.println("Hash map: " + map);
    }
}
