package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.util.UserDataField;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
        }
        response.sendRedirect("/login");
    }

}