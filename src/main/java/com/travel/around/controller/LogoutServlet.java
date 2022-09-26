package com.travel.around.controller;

import com.travel.around.model.User;
import com.travel.around.model.utils.UserStatus;
import com.travel.around.service.ApplicationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    HttpSession httpSession;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ApplicationService applicationService = ApplicationService.getInstance();

        httpSession = request.getSession(false);
        User user = (User) httpSession.getAttribute("user");
        user.setStatus(UserStatus.SIGNED_OUT);
        applicationService.userDao.updateUserStatus(user);

        httpSession.invalidate();

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
