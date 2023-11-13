package ru.webkonditer.service;

import java.util.Map;

import static ru.webkonditer.repo.DatabaseManager.getPalindromesForUser;
import static ru.webkonditer.repo.DatabaseManager.getUsersWithTotalScore;

public class UserService {

    // Метод проверки,  писал ли пользователь подобный палиндром
    public static boolean checkUserPalindrome(String user, String palindrome) {
        return getPalindromesForUser(user).stream()
                .anyMatch(element -> element.equals(palindrome));
    }

    // Метод для получения очков пользователя
    public static int getUserScope(String user) {
        return getUsersWithTotalScore().get(user);
    }

    // Метод для формирования строки ответа со списком всех пользователей и их очков
    public static String usersScopes() {

        Map<String, Integer> usersScopes = getUsersWithTotalScore();

        StringBuilder response = new StringBuilder();
        response.append("<html><head><title>List of users</title></head><body>");
        response.append("<h1>List of users and their points:</h1>");
        response.append("<table border='1'>");
        response.append("<tr><th>User</th><th>Scopes</th></tr>");

        for (String user : usersScopes.keySet()) {
            response.append("<tr><td>").append(user).append("</td><td>").append(usersScopes.get(user)).append("</td></tr>");
        }

        response.append("</table>");
        response.append("</body></html>");

        return response.toString();
    }
}
