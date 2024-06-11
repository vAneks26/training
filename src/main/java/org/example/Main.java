package org.example;


import org.apache.commons.codec.DecoderException;

import java.util.Scanner;


public class Main {
    private static DbConnection database = new DbConnection();
    private static Scanner scanner = new Scanner(System.in);
    private static Crud crudoper = new Crud(database);

    public static void main(String[] args) throws DecoderException {

        while (true) {
        System.out.println("1. регистрация User");
        // POST locolhost:8080/user body: {"email": "wxwx", "password": "1234"}
        System.out.println("2. Добавить тренировку");
            // POST locolhost:8080/workout body: {"email": "wxwx", "password": "1234"}
        System.out.println("3. Получить пользователя,пароль и тренировку по id");
            // POST locolhost:8080/user body: {"email": "wxwx", "password": "1234"}
        System.out.println("5. Обновить пользователя");
            // PUT locolhost:8080/user/id body: {"email": "wxwx", "password": "1234"}
        System.out.println("6. Обновить тренировку");
            // POST locolhost:8080/user body: {"email": "wxwx", "password": "1234"}
        System.out.println("7. Удалить пользователя");
            // DELETE locolhost:8080/user/id
        System.out.println("8. Удалить тренировку");
            // POST locolhost:8080/user body: {"email": "wxwx", "password": "1234"}
        System.out.println("9. логин и трен");
        int choice = Main.scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Логин");
                String name = scanner.next();
                System.out.println("Пароль");
                String password = scanner.next();
                crudoper.addUser(name, password);
                break;
            case 10:

            case 2:
                System.out.println("Введите id пользователя:");
                int userId = scanner.nextInt();
                System.out.println("Введите дату тренировки в формате ГГГГ-ММ-ДД:");
                String date = scanner.next();
                System.out.println("Введите описание тренировки:");
                String description = scanner.next();
                crudoper.AddWorkout(userId, date, description);
                break;
            case 3:
                System.out.println("Введите id пользователя:");
                userId = scanner.nextInt();
                User user = crudoper.getUser(userId);
                if (user != null) {
                    System.out.println("Имя пользователя: " + user.getName());
                    System.out.println("Пароль: " + user.getPassword());
                } else {
                    System.out.println("Пользователь не найден");
                }
                Workout workout = crudoper.getWorkout(userId);
                if (workout != null) {
                    System.out.println("Дата тренировки: " + workout.getDate());
                    System.out.println("Описание тренировки: " + workout.getDescription());
                } else {
                    System.out.println("Тренировка не найдена");
                }
                break;
            case 5:
                System.out.println("Введите id пользователя:");
                userId = scanner.nextInt();
                System.out.println("Введите новое имя пользователя:");
                name = scanner.next();
                user = crudoper.getUser(userId);
                if (user != null) {
                    user.setName(name);
                    crudoper.updateUser(user);
                } else {
                    System.out.println("Пользователь не найден");
                }
                break;
            case 6:
                System.out.println("Введите id тренировки:");
                userId = scanner.nextInt();
                System.out.println("Введите новую дату тренировки в формате ГГГГ-ММ-ДД:");
                date = scanner.next();
                System.out.println("Введите новое описание тренировки:");
                description = scanner.next();
                workout = crudoper.getWorkout(userId);
                if (workout != null) {
                    workout.setDate(date);
                    workout.setDescription(description);
                    crudoper.updateWorkout(workout);
                } else {
                    System.out.println("Тренировка не найдена");
                }
            case 7:
                System.out.println("Введите идентификатор пользователя для удаления:");
                int idi = scanner.nextInt();
                crudoper.deleteUser(idi);
                break;
            case 8:
                System.out.println("Введите идентификатор пользователя для удаления:");
                int idW = scanner.nextInt();
                crudoper.deleteWorkout(idW);
                break;
            case 9:
                System.out.println("Введите id");
                int idJoin = scanner.nextInt();
                System.out.println(crudoper.testGet(idJoin).getInfo());
                break;
        }
        }
    }
}


