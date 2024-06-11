package org.example;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class Crud {
    private final DbConnection database;

    public Crud(DbConnection database) {
        this.database = database;
    }

    public void addUser(String name,String password) {
        String salt = "sfdsdgseaf";
        // Преобразуем пароль в массив байтов
        byte[] passwordBytes = password.getBytes();
// Кешируем пароль с помощью алгоритма SHA-256
        String hashedPassword = DigestUtils.sha256Hex(passwordBytes+salt);
        try {
            PreparedStatement statement = this.database.getConnection().prepareStatement("INSERT INTO users (name,password) VALUES (?,?)");
            statement.setString(1, name);
            statement.setString(2, hashedPassword);
            statement.execute();
// получаем ключ/Извлекаем индекс/создаем нового юзера со id и name паролем
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                database.getUsers().put(id, new User(id, name,hashedPassword));
            }
        } catch (SQLException e) {
            System.out.println("не удалось зарегать юзера");;
        }
    }




// добавляем нового юзера
//public void addUser(String name, String password) throws DecoderException {
//    // Генерируем случайную соль для каждого пользователя
//   String salt = generateSalt();
//    // Кешируем пароль с использованием соли
//    String hashedPasswords = hashPassword(password, salt);
//    try {
//        // Создаем запрос для добавления нового пользователя с солью
//        PreparedStatement statement = this.database.getConnection().prepareStatement("INSERT INTO users (name, hashedPasswords) VALUES (?, ?)");
//        statement.setString(1, name);
//        statement.setString(2, hashedPasswords);
//        statement.execute();
//        // Получаем ключ/извлекаем индекс/создаем нового пользователя с id и именем/паролем
//        ResultSet resultSet = statement.getGeneratedKeys();
//        if (resultSet.next()) {
//            int id = resultSet.getInt(1);
//            database.getUsers().put(id, new User(id, name, hashedPasswords));
//        }
//    } catch (SQLException e) {
//        System.out.println("Не удалось зарегистрировать пользователя");
//    }
//}
//
//    // Метод для кеширования пароля с использованием соли
//    private String hashPassword(String password, String salt) throws DecoderException {
//        // Преобразуем пароль и соль в массивы байтов
//        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
//        byte[] saltBytes = Hex.decodeHex(salt.toCharArray());
//
//        // Конкатенируем пароль и соль
//
//        byte[] saltedPassword = ArrayUtils.addAll(passwordBytes, saltBytes);
//
//        // Кешируем пароль и соль с использованием SHA-256
//        String hashedPassword = DigestUtils.sha256Hex(saltedPassword);
//
//        return hashedPassword;
//    }



    public void AddWorkout(int userId, String date, String description) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement("INSERT INTO workouts (user_id, date, description) VALUES (?, ?, ?)");
            statement.setInt(1, userId);
            statement.setString(2, date);
            statement.setString(3, description);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                database.getWorkouts().put(id, new Workout(id, userId, date, description));
            }
        } catch (SQLException e) {
            System.out.println("не удалось добавить пользователя");
        }
    }

    public User getUser(int id) {
        return database.getUsers().get(id);
    }

    public Workout getWorkout(int id) {
        return database.getWorkouts().get(id);
    }
    public void updateUser(@org.jetbrains.annotations.NotNull User user) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement("UPDATE users SET name = ? WHERE id = ?");
            statement.setString(1, user.getName());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            database.getUsers().put(user.getId(), user);
        } catch (SQLException e) {
            System.out.println("не удалось обновить пользователя");
        }
    }
    public void updateWorkout(Workout workout) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement("UPDATE workouts SET user_id = ?, date = ?, description = ? WHERE id = ?");
            statement.setInt(1, workout.getUserId());
            statement.setString(2, workout.getDate());
            statement.setString(3, workout.getDescription());
            statement.setInt(4, workout.getId());
            statement.executeUpdate();
            database.getWorkouts().put(workout.getId(), workout);
        } catch (SQLException e) {
            System.out.println("не удалось обновить трен");
        }
    }
//    private static boolean authenticateUser(String username, String password) {
//        try (Connection conn = DriverManager.getConnection(DB_URL);
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            try (ResultSet rs = stmt.executeQuery()) {
//                return rs.next();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}

public userW testGet (int userId) {
        userW uw = null;
        try {
            PreparedStatement statement = database.getConnection().prepareStatement("SELECT users.name, workouts.date, workouts.description FROM users JOIN workouts ON users.id = workouts.user_id AND users.id=?");
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                String date = resultSet.getString(2);
                String description = resultSet.getString(3);
                uw = new userW(name, date, description);
            }

        } catch (SQLException e) {
            System.out.println("не удалось получить все");
        }
        return uw;
    }

    public void deleteUser(int id) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            database.getUsers().remove(id);
        } catch (SQLException e) {
            System.out.println("не удалось удалить пользователя");
        }
    }
    public void deleteWorkout(int id) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM workouts WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            database.getWorkouts().remove(id);
        } catch (SQLException e) {
            System.out.println("не удалось уд трен");
        }
    }


}



