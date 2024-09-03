package org.example.Collection.Class;

import java.util.HashMap;
import java.util.Map;

public class HashMap2 {
    public static void main(String[] args) {
        // Исходная структура JSON
        HashMap<String, Object> controlMap = new HashMap<>();
        controlMap.put("control", "-1");

        HashMap<String, Object> templateMigrationMap = new HashMap<>();
        templateMigrationMap.put("template_migration", controlMap);

        HashMap<String, Object> indexStateManagementMap = new HashMap<>();
        indexStateManagementMap.put("index_state_management", templateMigrationMap);

        HashMap<String, Object> pluginsMap = new HashMap<>();
        pluginsMap.put("plugins", indexStateManagementMap);

        HashMap<String, Object> persistentMap = new HashMap<>();
        persistentMap.put("persistent", pluginsMap);

        // Плоская структура
        HashMap<String, Object> flattenedMap = new HashMap<>();
        flattenMap("", persistentMap, flattenedMap);

        // Вывод плоской структуры
        System.out.println(flattenedMap);
    }
    //sourceMap исходная карта, содержащая данные, которые нужно "развернуть".
    // destinationMap целевая карта, куда будут записаны плоские ключи и значения.

    public static void flattenMap(String prefix, Map<String, Object> sourceMap, Map<String, Object> destinationMap) {
        // Проходим по каждому элементу (ключ-значение) в исходной карте
        for (Map.Entry<String, Object> entry : sourceMap.entrySet()) {
            // Формируем новый ключ. Если префикс пустой, используем текущий ключ как есть.
            // Если префикс не пустой, добавляем его перед текущим ключом через точку.
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();

            // Получаем значение по текущему ключу
            Object value = entry.getValue();

            // Если значение является еще одной картой (вложенная карта),
            if (value instanceof Map) {
                // рекурсивно вызываем flattenMap, чтобы обработать вложенные карты.
                // В этом случае, текущий ключ становится частью префикса для последующих ключей.
                flattenMap(key, (Map<String, Object>) value, destinationMap);

            } else {
                // Если значение не является картой, добавляем текущую пару ключ-значение в целевую (плоскую) карту.
                destinationMap.put(key, value);

            }
        }
    }

}
//flattenMap - Этот метод получает три параметра:
//        - prefix: строка-префикс для формирования ключей.
//        - sourceMap: исходная карта, содержащая данные, которые нужно "развернуть".
//        - destinationMap: целевая карта, куда будут записаны плоские ключи и значения.
//        - Метод проходит через каждую пару "ключ-значение" в sourceMap.
//        - Если значение является картой, он рекурсивно вызывает себя для обработки этой вложенной карты.
//        - Если значение не является картой, пара "ключ-значение" добавляется в destinationMap.


//Исходные данные:
//
//Инициализируем несколько HashMap, вложенных друг в друга, чтобы соответствовать структуре JSON, описанной в вопросе.
//Функция flattenMap:
//
//Рекурсивная функция, которая проходит по каждому элементу в исходной карте.
//Если элемент является картой, функция рекурсивно вызывается с обновленным ключом, который включает в себя текущий префикс.
//Если элемент является конечным значением (не карта), он добавляется в плоскую карту с полным ключом, созданным из
// префикса и текущего ключа.
//Вывод результата:
//
//В результате работы программы плоская карта (flattenedMap) будет содержать только один ключ:
// "persistent.plugins.index_state_management.template_migration.control" с соответствующим значением "-1".