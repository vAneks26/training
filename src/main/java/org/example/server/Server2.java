package org.example.server;

import com.sun.net.httpserver.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.sql.*;
import java.util.Base64;


public class Server2 {
    private static Connection connection;
    private static final Logger logger = LogManager.getLogger(Server2.class);

    public static void main(String[] args) throws Exception {
        // Подключение к базе данных SQLite
        String url = "jdbc:sqlite:/Users/vaneks/java/Diary/diary.db";
        try {
            connection = DriverManager.getConnection(url);
            logger.info("Успешное подключение к базе данных");
        } catch (SQLException e) {
            logger.error("Не удалось подключиться к базе данных", e);
        }

        // Настройка HTTPS сервера
        HttpsServer server = HttpsServer.create(new InetSocketAddress(8443), 0);
        //Создает объект SSLContext, который используется для управления шифрованием SSL/TLS.
        SSLContext sslContext = SSLContext.getInstance("TLS");

        //Задает пароль для доступа к KeyStore.
        char[] password = "qwerty".toCharArray();
        //Создает объект KeyStore для хранения сертификатов и ключей, используя формат JKS.
        KeyStore ks = KeyStore.getInstance("JKS");
        //Открывает файл KeyStore, который находится в указанном пути.
        FileInputStream fis = new FileInputStream("/Users/vaneks/java/Diary/server.jks");
        //Загружает KeyStore из файла с заданным паролем.
        ks.load(fis, password);

        //Создает объект KeyManagerFactory для управления ключами.
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        //Инициализирует KeyManagerFactory с помощью загруженного KeyStore и пароля.
        kmf.init(ks, password);
        //Создает объект TrustManagerFactory для управления сертификатами доверия.
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        //Инициализирует TrustManagerFactory с помощью загруженного KeyStore.
        tmf.init(ks);
        //Инициализирует SSLContext с помощью ключей (kmf.getKeyManagers()) и сертификатами доверия
        // (tmf.getTrustManagers()). null используется для инициализации генератора случайных чисел.
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        //Устанавливает HttpsConfigurator для сервера, который будет управлять параметрами SSL/TLS для каждого соединения.
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
            public void configure(HttpsParameters params) {
                try {
                    //Получает стандартный SSLContext, который используется по умолчанию в JVM.
                    SSLContext context = SSLContext.getDefault();
                    // Создает объект SSLEngine, который управляет шифрованием SSL/TLS.
                    SSLEngine engine = context.createSSLEngine();
                    params.setNeedClientAuth(false);
                    SSLParameters defaultSSLParameters = context.getDefaultSSLParameters();
                    //Устанавливает стандартные параметры SSL/TLS для соединения.
                    params.setSSLParameters(defaultSSLParameters);
                } catch (Exception e) {
                    logger.error("Ошибка настройки HTTPS", e);
                }
            }
        });

        logger.info("Успешное подключение к серверу");

        // Обработка корневого пути
        server.createContext("/", new Handler());

        // Обработка пользователей
        server.createContext("/users", exchange -> {
            // Получение HTTP-метода
            String method = exchange.getRequestMethod();

            // Выбор обработчика в зависимости от метода
            if (method.equals("GET")) {
                new AuthHandler(new GetHandler()).handle(exchange);
            } else if (method.equals("POST")) {
                new PostHandler().handle(exchange);
            } else if (method.equals("PUT")) {
                new PutHandler().handle(exchange);
            } else if (method.equals("DELETE")) {
                new DeleteHandler().handle(exchange);
            } else {
                exchange.sendResponseHeaders(405, 0);
                exchange.close();
            }
        });
        server.setExecutor(null); // Количество потоков
        server.start();
        logger.info("Сервер запущен и доступен по HTTPS на порту 8443");
    }

    static class AuthHandler implements HttpHandler {
        private final HttpHandler nextHandler;

        public AuthHandler(HttpHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            //exchange.getRequestHeaders().getFirst("Authorization");: Извлекает заголовок "Authorization" из HTTP-запроса.
            // Этот заголовок содержит информацию об аутентификации.
            String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                String encodedCredentials = authHeader.substring("Basic ".length());
                //Декодирует учетные данные, используя Base64, и преобразует их в строку.
                String credentials = new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);
                String[] parts = credentials.split(":");
                String name = parts[0];
                String password = parts[1];
                //Проверяет учетные данные, используя метод authenticate. Этот метод не показан в коде, но он должен проверять
                // правильность имени пользователя и пароля.
                if (authenticate(name, password)) {
                    nextHandler.handle(exchange);
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
                logger.error("Ошибка при аутентификации", e);
                return false;
            }
        }
    }

    static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "прив";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class GetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery();
            String name = getQueryParam(query, "name");

            String data = getFromDatabase(name);
            byte[] response = data.getBytes();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(response.length));
            exchange.sendResponseHeaders(200, response.length);

            // Записать тело ответа
            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(response);
            }
        }

        private String getFromDatabase(String name) {
            StringBuilder response = new StringBuilder();
            try {
                String query = "SELECT * FROM users WHERE name = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, name);
                    ResultSet resultSet = statement.executeQuery();

                    // Формирование ответа на основе данных из базы
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String userName = resultSet.getString("name");
                        String password = resultSet.getString("password");
                        response.append("{")
                                .append("\"ID\":").append(id).append(", ")
                                .append("\"Имя\":\"").append(userName).append("\", ")
                                .append("\"пароль\":\"").append(password).append("\"")
                                .append("}\n");
                    }
                }
            } catch (SQLException e) {
                logger.error("Не удалось подключиться к базе данных", e);
            }
            return response.toString();
        }

        private String getQueryParam(String query, String param) {
            if (query == null) {
                return null;
            }
            //Разделяет строку запроса (query) по символу "&", создавая массив строк, где каждая строка представляет
            // пару ключ-значение (например, "name=John&age=30").
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                //Разделяет пару ключ-значение (pair) по символу "=", создавая массив из двух строк: ключа (keyValue[0]) и значения (keyValue[1]).
                String[] keyValue = pair.split("=");
                //Проверяет, является ли длина массива keyValue равной 2 (чтобы убедиться, что разделение произошло корректно)
                // и совпадает ли ключ (keyValue[0]) с заданным параметром (param).
                if (keyValue.length == 2 && keyValue[0].equals(param)) {
                    //Если ключ совпадает с параметром, функция возвращает значение параметра (keyValue[1]).
                    return keyValue[1];
                }
            }
            return null;
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
            logger.error("Не удалось сохранить пользователя в базу данных", e);
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
                logger.error("Не удалось обновить пользователя в базе данных", e);
            }
        }
    }

    static class DeleteHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Извлеките логин пользователя из URL-адреса

            String uri = exchange.getRequestURI().toString();
            String login = uri.substring("/users/".length());
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
            logger.error("Не удалось удалить пользователя из базы данных", e);
        }
    }

}






