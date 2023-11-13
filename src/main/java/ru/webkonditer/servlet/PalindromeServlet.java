package ru.webkonditer.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ru.webkonditer.service.PalindromeService.checkPalindrome;
import static ru.webkonditer.service.PalindromeService.savePalindrome;
import static ru.webkonditer.service.UserService.checkUserPalindrome;
import static ru.webkonditer.service.UserService.getUserScope;

public class PalindromeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получение значения параметров "user" и "palindrom" из POST-запроса
        req.setCharacterEncoding("UTF-8");
        String user = req.getParameter("user");
        String palindrome = req.getParameter("palindrome");

        // Подготовка ответа
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // Проверка на палиндром
        int checkPalindrome = checkPalindrome(palindrome);

        PrintWriter writer = resp.getWriter();
        if (checkPalindrome == -1) {
            writer.write("Your string is not a palindrome");
        } else {
            // Проверяем уникальность палиндрома у пользователя
            if (checkUserPalindrome(user, palindrome)) {
                writer.write("You have already sent such a palindrome.");
            } else {
                // Сохраняем палиндром
                savePalindrome (user, palindrome);
                // Получаем очки текущего пользователя
                int userScope = getUserScope(user);
                // Отправляем пользователю ответ
                writer.write("Your string (" + palindrome + ") is a palindrome. Your score is " + userScope + " points.");
            }
        }
        writer.flush();
    }
}

