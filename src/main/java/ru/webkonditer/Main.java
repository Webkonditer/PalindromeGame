package ru.webkonditer;

import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.webkonditer.servlet.PalindromeServlet;
import ru.webkonditer.servlet.UsersScopesServlet;

import static ru.webkonditer.repo.DatabaseManager.createTable;

public class Main {
    public static void main(String[] args) throws Exception {

        createTable(); // создаем таблицу в бд, если не существует

        Server server = new Server(8080); // Порт, на котором будет работать сервер

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        server.setHandler(context);

        // Добавляем сервлеты
        context.addServlet(new ServletHolder(new PalindromeServlet()), "/new-palindrome");
        context.addServlet(new ServletHolder(new UsersScopesServlet()), "/users-scopes");

        server.start();
        server.join();
    }
}
