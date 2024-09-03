package org.example.Reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Извлечение доменного имени из URL
public class Reg_11 {
    public static void main(String[] args) {
        // Исходная строка с URL
        String url = "https://www.example.com";

// Создаем объект Pattern с регулярным выражением для извлечения доменного имени
        Pattern pattern = Pattern.compile("https?://(www\\.)?([^/]+)");

// Создаем объект Matcher, который будет искать доменное имя в строке
        Matcher matcher = pattern.matcher(url);

// Проверяем, есть ли совпадение по регулярному выражению
        if (matcher.find()) {
            // Выводим доменное имя (вторая захваченная группа)
            System.out.println(matcher.group(2)); // example.com
        }

    }
}
