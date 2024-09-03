package org.example.Reg;


// Замена пробелов на подчеркивания
public class reg_5 {
    public static void main(String[] args) {
        // Исходная строка с пробелами
        String input = "Replace all spaces";

// Заменяем все пробелы одним или более пробелов на подчеркивания
        String result = input.replaceAll("\\s+", "_");


        System.out.println(result);

    }
}
