package org.example.Reg;

public class Reg_14 {
    public static void main(String[] args) {
        // Исходная строка с HTML-тегами
        String input = "<p>This is a <strong>bold</strong> statement.</p>";

        // Регулярное выражение для удаления HTML-тегов
        String regex = "<[^>]+>";

        // Удаляем все HTML-теги
        String result = input.replaceAll(regex, "");

        // Выводим результат
        System.out.println(result);
    }
}


