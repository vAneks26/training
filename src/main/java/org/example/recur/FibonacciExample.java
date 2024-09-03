package org.example.recur;

public class FibonacciExample {
    public static void main(String[] args) {
        int number = 10; // Вычислим 10-е число Фибоначчи
        int result = fibonacci(number);
        System.out.println("Число Фибоначчи для " + number + " равно " + result);
    }

    // Рекурсивный метод для вычисления числа Фибоначчи
    public static int fibonacci(int n) {
        if (n == 0) {  // Базовый случай: F(0) = 0
            return 0;
        } else if (n == 1) {  // Базовый случай: F(1) = 1
            return 1;
        } else {  // Рекурсивный случай: F(n) = F(n-1) + F(n-2)
            return fibonacci(n - 1) + fibonacci(n - 2);
            //Для любого n, большего 1, возвращает сумму двух предыдущих чисел
        }
    }
}
//При вызове fibonacci(10), метод рекурсивно вычисляет:
//fibonacci(10) вызывает fibonacci(9) и fibonacci(8).
//fibonacci(9) вызывает fibonacci(8) и fibonacci(7), и так далее, пока не достигнуты базовые случаи fibonacci(0) и fibonacci(1).