package org.example.Reg;

//Поиск и замена в тексте
public class Reg_13 {
    public static void main(String[] args) {

        // Исходная строка
        String input = "The1color1is red.";

        // Регулярное выражение для поиска слова "color"
        String regex = "\\bcolor\\b";

        // Замена найденного слова на "colour"
        String result = input.replaceAll(regex, "colour");

        // Выводим результат
        System.out.println(result);
    }
}


