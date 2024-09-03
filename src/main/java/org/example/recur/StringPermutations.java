package org.example.recur;


import java.util.HashMap;

//Рекурсивная функция может использоваться для генерации и вывода всех возможных перестановок символов строки.
public class StringPermutations {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> innerMap = new HashMap<>();
        innerMap.put("a", "b");
        map.put("c", innerMap);
        int p = 0;
    }

    // Рекурсивный метод для генерации перестановок строки
    public static void permute(String str, String prefix) {
        if (str.length() == 0) {  // Базовый случай: строка пустая
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                // Выбираем текущий символ
                char currentChar = str.charAt(i);
                // Оставшаяся строка без текущего символа
                String remainingString = str.substring(0, i) + str.substring(i + 1);
                // Рекурсивный вызов с новой строкой и добавленным префиксом
                permute(remainingString, prefix + currentChar);
            }
        }
    }
}
//Цикл for проходит по каждому символу строки str.
//Извлекается текущий символ (currentChar).
//Создается строка remainingString, которая содержит все символы строки str, кроме текущего символа.
//Рекурсивно вызывается метод permute с новой строкой remainingString и обновленным префиксом (prefix + currentChar).

//Для строки "ABC", процесс будет следующим:
//
//Первая итерация:
//
//currentChar = 'A'
//remainingString = "BC"
//Рекурсивный вызов: permute("BC", "A")