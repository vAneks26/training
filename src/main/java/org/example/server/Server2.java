package org.example.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Base64;


public class Server2 {
    private static Connection connection;

    public static void main(String[] args) throws IOException {
        String url = "jdbc:sqlite:/Users/vaneks/java/Diary/diary.db";
        // Подключение к базе данных SQLite
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных");
            e.printStackTrace();
        }
        try {


            //создаем соединение с порт
            HttpServer server = HttpServer.create(new InetSocketAddress(8083), 0);

            //обработка адресных путей
            // Обработка адресных путей
            server.createContext("/", new Handler());
            server.createContext("/user", new AuthHandler(new GetHandler()));
            server.createContext("/use", new PostHandler());
            server.createContext("/put", new PutHandler());
            server.createContext("/delete", new DeleteHandler());
            server.setExecutor(null); // Количество потоков
            server.start();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    static class AuthHandler implements HttpHandler {
        private final HttpHandler nextHandler;

        public AuthHandler(HttpHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            //извлекает заголовок запроса Auth и сохраняет его в переменной authHeader.
            String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
//если заголовок "Authorization" не равен null и начинается с "Basic ", это означает, что используется
// Basic. В этом случае блок кода внутри выполняется.
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                //Из заголовка "Authorization" извлекаются закодированные учетные данные (имя пользователя и пароль).
                String encodedCredentials = authHeader.substring("Basic ".length());
                //Закодированные учетные данные декодируются с помощью Base64 и преобразуются в строку credentials.
                String credentials = new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);
                String[] parts = credentials.split(":");
                String name = parts[0];
                String password = parts[1];
//Выполняется метод authenticate для проверки предоставленных учетных данных.
// Если проверка прошла успешно, код внутри блока if выполняется.
                if (authenticate(name, password)) {
                    nextHandler.handle(exchange);// Передача управления следующему обработчику
                    return;
                }
            }
            // Auth Failed!
            exchange.sendResponseHeaders(401, 0);
            exchange.getResponseBody().write("Not Authorized!".getBytes());
            exchange.close();
        }
        private boolean authenticate(String name, String password) {
            String url = "jdbc:sqlite:/Users/vaneks/java/Diary/diary.db";
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, name);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();
                    return rs.next(); // Если найден пользователь, возвращаем true
                }
            } catch (SQLException e) {
                System.out.println("нет authenticate");;
                e.printStackTrace();
                return false;
            }
        }

    }


    static class Handler implements HttpHandler {
        @Override
        //запрос от сервера, можем считывать и передавать данные/делать ответы и запросы через exchange
        public void handle(HttpExchange exchange) throws IOException {
            String response = "прив";
            // пришли ответ респонс/заголовок ответа
            exchange.sendResponseHeaders(200, response.getBytes().length);
            // внутренности ответа
            OutputStream os = exchange.getResponseBody();
            // читает наш респонс
            os.write(response.getBytes());
            os.close();
        }
    }

    static class GetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String data = getFromDatabase();
            byte[] response = data.getBytes();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(response.length));
            exchange.sendResponseHeaders(200, response.length);

            // Записать тело ответа
            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(response);
            }
        }

        private String getFromDatabase() {
            StringBuilder response = new StringBuilder();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

                // Формирование ответа на основе данных из базы
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    response.append("ID: ").append(id).append(", Имя: ").append(name).append(", пароль: ").append(password).append("\n");
                }
            } catch (SQLException e) {
                System.out.println("Не удалось выполнить запрос к базе данных");
                e.printStackTrace();
                response.append("Ошибка при получении данных из базы");
            }
            return response.toString();
        }
    }

    static class PostHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            //Получить тело запроса
            //Создается InputStreamReader, который преобразует байты из тела запросав символы, используя кодировку UTF-8.
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            //Создается BufferedReader, который оборачивает InputStreamReader и предоставляет удобные методы для построчного чтения текста.
            BufferedReader br = new BufferedReader(isr);
            //Создается StringBuilder для хранения собранного содержимого тела запроса.
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                //Каждая прочитанная строка добавляется в StringBuilder requestBody.
                requestBody.append(line);
            }
            // Разобрать тело запроса и извлечь данные пользователя и пароля
            JSONObject json = new JSONObject(requestBody.toString());
            String name = json.getString("name");
            String password = json.getString("password");

            // Сохранить пользователя и пароль в базу данных
            saveUserToDatabase(name, password);

            // Отправить ответ
            byte[] response = "Пользователь успешно создан".getBytes();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(response.length));
            exchange.sendResponseHeaders(201, response.length);

            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(response);
            }
        }
    }

    private static void saveUserToDatabase(String name, String password) {
        String salt = "sfdsdgs";
        // Преобразуем пароль в массив байтов
        byte[] passwordBytes = password.getBytes();
// Кешируем пароль с помощью алгоритма SHA-256
        String hashedPassword = DigestUtils.sha256Hex(passwordBytes + salt);
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, password) VALUES (?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, hashedPassword);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить пользователя в базу данных: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static class PutHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Получить тело запроса
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                requestBody.append(line);
            }

            // Разобрать тело запроса и извлечь данные пользователя и пароля
            JSONObject json = new JSONObject(requestBody.toString());
            int id = json.getInt("id");
            String name = json.getString("name");
            String password = json.getString("password");

            // Обновить пользователя в базе данных
            updateUserDatabase(id, name, password);

            // Отправить ответ
            byte[] response = "Пользователь успешно обновлен".getBytes();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(response.length));
            exchange.sendResponseHeaders(201, response.length);

            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(response);
            }
        }

        private static void updateUserDatabase(int id, String name, String password) {
            String salt = "sfdsdgs";
            byte[] passwordBytes = password.getBytes();
            String hashedPassword = DigestUtils.sha256Hex(passwordBytes + salt);

            try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, password = ? WHERE id = ?")) {
                statement.setString(1, name);
                statement.setString(2, hashedPassword);
                statement.setInt(3, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Не удалось обновить пользователя в базе данных: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    static class DeleteHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Извлеките логин пользователя из URL-адреса

            String uri = exchange.getRequestURI().toString();
            String login = uri.substring("/delete/".length());
            // Удалите пользователя из базы данных SQLite
            DeleteUserToDatabase(login);

            // Создайте сообщение об успешном удалении
            String response = "Пользователь с логином " + login + " успешно удален";
//он преобразует строку ответа (response) в массив байтов (responseBytes).
// Это необходимо, потому что тела ответов HTTP должны быть представлены в виде массива байтов.
            byte[] responseBytes = response.getBytes();
            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(responseBytes.length));
            exchange.sendResponseHeaders(200, responseBytes.length);

            // Записать тело ответа
            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(responseBytes);
            }
        }
    }

    private static void DeleteUserToDatabase(String login) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE name = ?")) {
            statement.setString(1, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователя из базы данных: " + e.getMessage());
            e.printStackTrace();
        }
    }

}






