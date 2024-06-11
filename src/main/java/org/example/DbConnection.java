package org.example;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;


public class DbConnection {

    private Connection connection ;
    private Map<Integer, User> users;
    private Map<Integer, Workout> workouts;

    public DbConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:sqlite:/Users/vaneks/java/Diary/diary.db";
        try {
            connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS workouts (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER, date TEXT, description TEXT, FOREIGN KEY (user_id) REFERENCES users (id))");



            users = new HashMap<>();
            workouts = new HashMap<>();


                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                users.put(id, new User(id, name,password));

            }
            resultSet = statement.executeQuery("SELECT * FROM workouts");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                String date = resultSet.getString("date");
                String description = resultSet.getString("description");
                workouts.put(id, new Workout(id, userId, date, description));
            }

        } catch (SQLException e) {
            System.out.println("не удалось получить трен или юзера");

        }
    }


    public Map<Integer, Workout> getWorkouts() {
        return workouts;
    }

    public Connection getConnection() {
        return connection;
    }


    public Map<Integer, User> getUsers() {
        return users;
    }


    }
