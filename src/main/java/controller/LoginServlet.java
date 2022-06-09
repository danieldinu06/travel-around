package controller;


import config.TemplateEngineUtil;
import model.User;
import model.utils.Encrypt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    User user;

    HttpSession httpSession;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        httpSession = request.getSession(true);
        if (httpSession.getAttribute("user") != null) response.sendRedirect("/");

        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        templateEngine.process("login.html", webContext, response.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = Encrypt.encode(request.getParameter("password"));

        ApplicationService applicationService = ApplicationService.getInstance();
        user = applicationService.userDao.get(name);

        if (user == null)
            doGet(request, response);

        if (!password.equals(user.getPassword()))
            doGet(request, response);

        applicationService.userDao.updateUserStatus(user);
        httpSession = request.getSession(true);
        httpSession.setAttribute("user", user);

        doGet(request, response);
    }
}
