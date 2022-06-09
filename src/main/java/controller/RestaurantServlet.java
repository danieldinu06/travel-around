package controller;

import config.TemplateEngineUtil;
import dao.RestaurantDao;
import dao.jdbc.RestaurantDaoJDBC;
import model.Restaurant;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/restaurant/id/*"})
public class RestaurantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        ApplicationService applicationService = ApplicationService.getInstance();
        Restaurant restaurant = applicationService.restaurantDao.get(1);
        webContext.setVariable("restaurant", restaurant);
        templateEngine.process("index.html", webContext, resp.getWriter());
    }
}
