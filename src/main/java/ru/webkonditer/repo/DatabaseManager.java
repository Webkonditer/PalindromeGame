package ru.webkonditer.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:C:/Users/777/IdeaProjects/PalindromGame/src/main/java/ru/webkonditer/repo/database.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    // Метод для получения списка пользователей с суммой их очков
    public static Map<String, Integer> getUsersWithTotalScore() {
        Map<String, Integer> usersWithScores = new TreeMap<>();

        // SQL-запрос для извлечения пользователей и суммы их очков из таблицы "palindromes"
        String sql = "SELECT user, SUM(score) AS total_score FROM palindromes GROUP BY user";

        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String userName = resultSet.getString("user");
                int totalScore = resultSet.getInt("total_score");
                usersWithScores.put(userName, totalScore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersWithScores;
    }

    // Метод для получения всех палиндромов одного пользователя
    public static List<String> getPalindromesForUser(String userName) {
        List<String> userPalindromes = new ArrayList<>();

        // SQL-запрос для извлечения палиндромов одного пользователя из таблицы "palindromes"
        String sql = "SELECT palindrome FROM palindromes WHERE user = ?";

        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String palindrome = resultSet.getString("palindrome");
                    userPalindromes.add(palindrome);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка ошибок
        }

        return userPalindromes;
    }

    // Метод для записи палиндрома в базу
    public static List<String> savePalindromeToDb(String user, String palindrome, int score) {
        List<String> userPalindromes = new ArrayList<>();

        String sql = "INSERT INTO palindromes (user, palindrome, score) VALUES (?, ?, ?)";

        try (Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, palindrome);
            preparedStatement.setInt(3, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userPalindromes;
    }

    // Метод для создания таблицы в БД
    public static void createTable() {
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS palindromes (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, palindrome TEXT, score INTEGER)")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

