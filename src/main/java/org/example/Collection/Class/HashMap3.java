package org.example.Collection.Class;

import java.util.HashMap;
import java.util.Map;

public class HashMap3 {
    public static void main(String[] args) {
        // Плоская структура с точечной нотацией ключей
        HashMap<String, Object> flattenedMap = new HashMap<>();
        flattenedMap.put("persistent.plugins.index_state_management.template_migration.control", "-1");

        // Восстановленная вложенная структура
        HashMap<String, Object> nestedMap = new HashMap<>();
        unflattenMap(flattenedMap, nestedMap);

        // Вывод восстановленной структуры
        System.out.println(nestedMap);
    }

    // Функция для преобразования плоской структуры в вложенную
    public static void unflattenMap(Map<String, Object> sourceMap, Map<String, Object> destinationMap) {
        for (Map.Entry<String, Object> entry : sourceMap.entrySet()) {
            String[] keys = entry.getKey().split("\\.");//забиваем массив по точке
            Map<String, Object> currentMap = destinationMap;

            // Проход по всем ключам, кроме последнего
            for (int i = 0; i < keys.length - 1; i++) {
                String key = keys[i];
                if (!currentMap.containsKey(key) || !(currentMap.get(key) instanceof Map)) {
                    currentMap.put(key, new HashMap<String, Object>());
                }
                currentMap = (Map<String, Object>) currentMap.get(key);
            }

            // Добавление конечного значения
            currentMap.put(keys[keys.length - 1], entry.getValue());
        }
    }
}

