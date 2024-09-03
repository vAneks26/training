package org.example.Collection.Class;

import java.util.HashSet;
import java.util.Set;

public class HashSet1 {
    public static void main(String[] args) {
        // Создаем hash set
        Set<String> set = new HashSet<>();

// Добавляем элементы в hash set
        set.add("rose");
        set.add("lily");
        set.add("lotus");
// Попытка добавить повторяющийся элемент
        set.add("rose");
// Печатаем hash set
        System.out.println("Hash set: " + set);
// Удаляем элемент из hash set
        set.remove("lily");
// Печать обновленного hash set
        System.out.println("Hash set: " + set);
    }
}
