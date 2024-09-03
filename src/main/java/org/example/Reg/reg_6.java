package org.example.Reg;


//Проверка на содержание только букв и цифр
public class reg_6 {
    public static void main(String[] args) {
        // Исходная строка
        String input = "abc123";

// Регулярное выражение для проверки, что строка содержит только буквы и цифры
        String regex = "^[a-zA-Z0-9]+$";

        if (input.matches(regex)) {
            System.out.println("Строка содержит только буквы и цифры");
        } else {
            System.out.println("Строка содержит другие символы");
        }

    }
}
