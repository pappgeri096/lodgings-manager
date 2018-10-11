package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/edit-profile"})
public class EditProfileController extends HttpServlet {

    private UserDaoDb userDataManager = new UserDaoDb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            WebContext context = new WebContext(request, response, request.getServletContext());
            User user = userDataManager.findIdBy(userEmail);
            context.setVariable("userData", user);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("edit_profile.html", context, response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
        WebContext context = new WebContext(request, response, request.getServletContext());
        User user = userDataManager.findIdBy(userEmail);
        context.setVariable("userData", user);

        String firstName = request.getParameter(UserDataField.FIRST_NAME.getInputString());
        String surname = request.getParameter(UserDataField.SURNAME.getInputString());
        String phoneNumber = request.getParameter(UserDataField.PHONE_NUMBER.getInputString());
        String country = request.getParameter(UserDataField.COUNTRY.getInputString());
        String city = request.getParameter(UserDataField.CITY.getInputString());
        String zipCode = request.getParameter(UserDataField.ZIP_CODE.getInputString());
        String address = request.getParameter(UserDataField.ADDRESS.getInputString());

        user.setFirstName(firstName);
        user.setSurname(surname);
        user.setPhoneNumber(phoneNumber);
        user.setCountry(country);
        user.setCity(city);
        user.setZipCode(zipCode);
        user.setAddress(address);

        try {
            userDataManager.update(user);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("New user could not be created");
        }

        response.sendRedirect("/profile");
    }

}

