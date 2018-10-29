package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Landlord;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.LodgingsType;
import com.codecool.lodgingsmanager.util.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

@WebServlet(urlPatterns = {"/add-lodgings"})
public class AddLodgingsController extends HttpServlet {

    private final BaseService<User> userHandler = Initializer.USER_HANDLER;
    private final BaseService<Lodgings> lodgingsHandler = Initializer.LODGINGS_HANDLER;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            WebContext context = new WebContext(request, response, request.getServletContext());
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            User user = userHandler.handleGetSingleObjectBy(userEmail);
            context.setVariable("userData", user);
            engine.process("add_lodgings.html", context, response.getWriter());
        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {

            String lodgingName = request.getParameter(LodgingDataField.NAME.getInputString());
            String lodgingType = request.getParameter(LodgingDataField.TYPE.getInputString());
            String country = request.getParameter(LodgingDataField.COUNTRY.getInputString());
            String city = request.getParameter(LodgingDataField.CITY.getInputString());
            String zipCode = request.getParameter(LodgingDataField.ZIP_CODE.getInputString());
            String address = request.getParameter(LodgingDataField.ADDRESS.getInputString());
            String dailyPrice = request.getParameter(LodgingDataField.DAILY_PRICE.getInputString());
            String electricityBill = request.getParameter(LodgingDataField.ELECTRICITY_BILL.getInputString());
            String gasBill = request.getParameter(LodgingDataField.GAS_BILL.getInputString());
            String telecommunicationBill = request.getParameter(LodgingDataField.TELECOMMUNICATION_BILL.getInputString());
            String cleaningCost = request.getParameter(LodgingDataField.CLEANING_COST.getInputString());

            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            User user = userHandler.handleGetSingleObjectBy(userEmail);


            Lodgings newLodgings = new Lodgings(
                    lodgingName,
                    LodgingsType.valueOf(lodgingType.toUpperCase()),
                    country,
                    city,
                    zipCode,
                    address,
                    Long.parseLong(dailyPrice),
                    Long.parseLong(electricityBill),
                    Long.parseLong(gasBill),
                    Long.parseLong(telecommunicationBill),
                    Long.parseLong(cleaningCost),
                    user
            );

            lodgingsHandler.handleAddNew(newLodgings);


            response.sendRedirect("/index");


        }


    }
}