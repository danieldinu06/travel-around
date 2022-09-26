package com.travel.around.controller;

import com.travel.around.config.TemplateEngineUtil;
import com.travel.around.model.User;
import com.travel.around.model.utils.Encrypt;
import com.travel.around.model.utils.UserStatus;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.travel.around.service.ApplicationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    User user;
    HttpSession httpSession;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        httpSession  = request.getSession(true);
        if (httpSession.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/");
        }

        templateEngine.process("register.html", webContext, response.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = Encrypt.encode(request.getParameter("password"));
        String passwordConfirmation = Encrypt.encode(request.getParameter("password-conf"));
        String phoneNumber = request.getParameter("phone-number");
        String billingAddress = request.getParameter("billing-address");
        UserStatus userStatus = UserStatus.SIGNED_IN;

        ApplicationService applicationService = ApplicationService.getInstance();
        User newUser = new User(name, email, password, phoneNumber, billingAddress, userStatus);
        user = applicationService.userDao.get(name);


        if (user != null) {
            if (user.getName().equals(newUser.getName())) {
                response.sendRedirect(request.getContextPath() + "/register");
                return;
            }
        }

        if (!(password.equals(passwordConfirmation))) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        httpSession = request.getSession(true);
        httpSession.setAttribute("user", newUser);
        applicationService.userDao.add(newUser);

//        SendEmail sendEmail = new SendEmail("danieldinu6091", "Registration Confirmation", "Thank you for registering to our site!");

        response.sendRedirect(request.getContextPath() + "/");
    }
}
