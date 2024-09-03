package org.example.Reg;

// Поиск строки, начинающейся с определенного слова
public class Reg_7 {
    public static void main(String[] args) {
        // Исходная строка
        String input = "Java is ergdfhb dherh";

// Регулярное выражение для проверки, что строка начинается с "Java"
        String regex = "^Java";

// Проверяем, начинается ли строка с "Java"
        if (input.matches(regex + ".*")) {
            // Если начинается, выводим соответствующее сообщение
            System.out.println("Строка начинается с 'Java'");
        } else {
            // Если не начинается, выводим соответствующее сообщение
            System.out.println("Строка не начинается с 'Java'");
        }

    }
}
