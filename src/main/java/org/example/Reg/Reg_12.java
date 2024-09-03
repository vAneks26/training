package org.example.Reg;

import java.util.regex.Pattern;

public class Reg_12 {
    public static void main(String[] args) {

        // Пример даты для проверки
        String date = "31/12/2023";

        // Регулярное выражение для формата даты DD/MM/YYYY
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

        // Проверяем, соответствует ли строка формату даты
        if (Pattern.matches(regex, date)) {
            System.out.println("Дата корректна");
        } else {
            System.out.println("Дата некорректна");
        }
    }
}

