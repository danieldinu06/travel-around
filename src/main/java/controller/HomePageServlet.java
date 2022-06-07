package controller;

import config.TemplateEngineUtil;
import model.TouristAttraction;
import model.User;
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
import java.util.List;

@WebServlet(name = "touristAttractionServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class HomePageServlet extends HttpServlet {
    HttpSession httpSession;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        ApplicationService applicationService = ApplicationService.getInstance();
        List<TouristAttraction> touristAttractions = applicationService.touristAttractionDao.getAll();

        httpSession = request.getSession(true);
        User user = (User) httpSession.getAttribute("user");

        if (user == null)
            response.sendRedirect(request.getContextPath() + "/login");

        webContext.setVariable("touristAttractions", touristAttractions);
        if (user != null) {
            webContext.setVariable("username", user.getName());
        }
        templateEngine.process("index.html", webContext, response.getWriter());
    }
}
