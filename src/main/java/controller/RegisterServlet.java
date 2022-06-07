package controller;

import config.TemplateEngineUtil;
import dao.UserDao;
import model.User;
import model.utils.Encrypt;
import model.utils.UserStatus;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    User user;
    HttpSession httpSession;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        templateEngine.process("register.html", webContext, response.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("name");
        String password = Encrypt.encode(request.getParameter("password"));
        String passwordConfirmation = Encrypt.encode(request.getParameter("password-conf"));
        String phoneNumber = request.getParameter("phone-number");
        String billingAddress = request.getParameter("billing-address");
        UserStatus userStatus = UserStatus.SIGNED_IN;

        ApplicationService applicationService = ApplicationService.getInstance();
        user = new User(name, password, phoneNumber, billingAddress, userStatus);

        if (applicationService.userDao.get(name) != null) {
            if ((applicationService.userDao.get(name) != user) || (!Encrypt.decode(password).equals(Encrypt.decode(passwordConfirmation)))) {
                response.sendRedirect(request.getContextPath() + "/register");
            }
        }

        httpSession = request.getSession(true);
        httpSession.setAttribute("user", user);

        applicationService.userDao.add(user);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
