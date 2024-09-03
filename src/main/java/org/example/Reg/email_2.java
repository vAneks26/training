package org.example.Reg;

public class email_2 {
    public static void main(String[] args) {
        // Определяем строку с email
        String email = "mister.shlykov1999@mail.ru";

// Регулярное выражение для проверки формата email
        String regex = "^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

// Проверяем, соответствует ли строка с email заданному регулярному выражению
        if (email.matches(regex)) {
            // Если соответствует, выводим, что email корректный
            System.out.println("Корректный email");
        } else {
            // Если не соответствует, выводим, что email некорректный
            System.out.println("Некорректный email");
        }
    }
}
