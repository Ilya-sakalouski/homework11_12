package ru.mail.ilya6697089.app.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.mail.ilya6697089.app.service.UserService;
import ru.mail.ilya6697089.app.service.impl.UserServiceImpl;

public class UserDeleteServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("id");
        userService.deleteUserById(Integer.parseInt(userId));

        resp.sendRedirect(req.getContextPath() + "/showUsers");
    }

}
