package controller;

import config.TemplateEngineUtil;
import dao.UserDao;
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

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    User user;
    HttpSession httpSession;

    private void setData(HttpServletRequest request, HttpServletResponse response) {
        httpSession = request.getSession();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String password = Encrypt.encode(request.getParameter("password"));
        String phoneNumber = request.getParameter("phone_number");
        String billingAddress = request.getParameter("billing_address");
        UserStatus userStatus = UserStatus.valueOf(request.getParameter("user_status"));

        user = new User(name, password, phoneNumber, billingAddress, userStatus);


        httpSession = request.getSession(true);
        httpSession.setAttribute("user", user);

        ApplicationService applicationService = ApplicationService.getInstance();
        applicationService.userDao.add(user);

        PrintWriter writer = response.getWriter();
        writer.println("register.html");
    }
}
