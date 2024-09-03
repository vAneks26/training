package org.example.recur;


//Обратное печатание строки
public class Str {
    public static void main(String[] args) {
        String str = "Hello, World!";
        reverse(str);
    }

    // Рекурсивный метод для обратной печати строки
    public static void reverse(String str) {
        if (str.isEmpty()) {  // Базовый случай: пустая строка
            return;
        } else {
            // Печатаем последний символ и вызываем метод для оставшейся строки
            System.out.print(str.charAt(str.length() - 1));
            reverse(str.substring(0, str.length() - 1));
        }
    }
}
//Метод reverse сначала проверяет, пустая ли строка. Если да, то он завершает выполнение.
//В противном случае он печатает последний символ строки и вызывает сам себя с оставшейся
// частью строки (без последнего символа), продолжая до тех пор, пока строка не станет пустой.