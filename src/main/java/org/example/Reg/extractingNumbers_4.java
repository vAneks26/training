package org.example.Reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Извлечение всех цифр из строки
public class extractingNumbers_4 {
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("\\d+");

        Matcher matcher = pattern.matcher("There are 12 cats and 9 dogs");

// Используем цикл, чтобы найти все совпадения чисел
        while (matcher.find()) {

            System.out.println(matcher.start()  + "  " + matcher.group());
        }


    }

}
