package org.example.recur;



public class Recursion {
    public static void main(String[] args) {
        int number = 5; // Число, для которого вычисляется факториал
        int result = factorial(number);
        System.out.println("Факториал " + number + " равен " + result);
    }

    // Рекурсивный метод для вычисления факториала
    public static int factorial(int n) {
        if (n == 1) {  // Базовый случай: факториал 1 равен 1
            return 1;
        } else {  // Рекурсивный случай: n * факториал (n-1)
            return n * factorial(n - 1);
        }
    }
}
//Для вычисления факториала 5:
//
//factorial(5) вызывает 5 * factorial(4)
//factorial(4) вызывает 4 * factorial(3)
//factorial(3) вызывает 3 * factorial(2)
//factorial(2) вызывает 2 * factorial(1)
//factorial(1) возвращает 1 (базовый случай)
//Сборка результата: 2 * 1 = 2, 3 * 2 = 6, 4 * 6 = 24, 5 * 24 = 120
