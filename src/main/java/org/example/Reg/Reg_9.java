package org.example.Reg;


//Замена повторяющихся пробелов на один
public class Reg_9 {
    public static void main(String[] args) {
        // Исходная строка с несколькими пробелами между словами
        String input = "sdgfsd   sdfs   sаfsd";

// Заменяем все последовательности пробелов на один пробел
        String result = input.replaceAll("\\s+", " ");

// Выводим результат
        System.out.println(result); // Too many spaces

    }
}
