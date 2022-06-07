package controller;

import config.TemplateEngineUtil;
import model.User;
import model.utils.UserStatus;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    HttpSession httpSession;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationService applicationService = ApplicationService.getInstance();

        httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        user.setStatus(UserStatus.SIGNED_OUT);
        applicationService.userDao.updateUserStatus(user);

        httpSession.invalidate();

        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        templateEngine.process("login.html", webContext, response.getWriter());
    }
}
