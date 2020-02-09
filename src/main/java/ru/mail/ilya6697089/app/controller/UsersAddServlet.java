package ru.mail.ilya6697089.app.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.mail.ilya6697089.app.service.UserService;
import ru.mail.ilya6697089.app.service.impl.UserServiceImpl;
import ru.mail.ilya6697089.app.service.model.UserDTO;

public class UsersAddServlet extends HttpServlet {

    private static final String PAGES = "/WEB-INF/pages";
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(PAGES + "/user.jsp");

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDTO userDTO = getUserDTO(req);

        userService.addUser(userDTO);

        resp.sendRedirect(req.getContextPath() + "/showUsers");
    }

    private UserDTO getUserDTO(HttpServletRequest req) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(req.getParameter("username"));
        userDTO.setPassword(req.getParameter("password"));
        userDTO.setCreatedBy(req.getParameter("createdBy"));
        userDTO.setName(req.getParameter("name"));
        userDTO.setDescription(req.getParameter("description"));

        return userDTO;
    }

}
