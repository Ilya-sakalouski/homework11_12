package ru.mail.ilya6697089.app.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.mail.ilya6697089.app.service.UserService;
import ru.mail.ilya6697089.app.service.impl.UserServiceImpl;
import ru.mail.ilya6697089.app.service.model.UserDTO;

public class UsersShowServlet extends HttpServlet {

    private static final String PAGES = "/WEB-INF/pages";
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDTO> users = userService.findAll();

        request.setAttribute("users", users);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(PAGES + "/users.jsp");

        requestDispatcher.forward(request, response);
    }

}
