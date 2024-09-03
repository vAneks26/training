package org.example.Reg;


//Удаление всех нецифровых символов из строки
public class Reg_8 {
    public static void main(String[] args) {

        String input = "Phone number: (123) 456-7890";

        String result = input.replaceAll("\\D+", "");

        System.out.println(result);

    }
}
