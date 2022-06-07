package controller;


import config.TemplateEngineUtil;
import model.User;
import model.utils.Encrypt;
import model.utils.UserStatus;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    User user;

    HttpSession httpSession;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        templateEngine.process("login.html", webContext, response.getWriter());

    }

}
